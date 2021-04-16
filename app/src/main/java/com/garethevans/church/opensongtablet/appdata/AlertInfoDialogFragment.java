package com.garethevans.church.opensongtablet.appdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.garethevans.church.opensongtablet.R;
import com.garethevans.church.opensongtablet.databinding.AlertinfoDialogBinding;
import com.garethevans.church.opensongtablet.interfaces.MainActivityInterface;

/*
This file shows the user any appropriate warnings.  These can be
- A reminder to create a backup file of songs
- Information about a recent update
- A warning about not having Google Play Services installed
 */

public class AlertInfoDialogFragment extends DialogFragment {

    private MainActivityInterface mainActivityInterface;
    private AlertinfoDialogBinding myView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivityInterface = (MainActivityInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = AlertinfoDialogBinding.inflate(inflater,container,false);
        if (getDialog()!=null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Show/hide the appropriate alerts
        whatAlerts();

        myView.dialogHeading.findViewById(R.id.close).setOnClickListener(b -> dismiss());

        return myView.getRoot();
    }

    private void whatAlerts() {
        // This decides which alerts are appropriate
        // Check for app updates
        boolean updateInfo = mainActivityInterface.getAlertChecks().showUpdateInfo(requireContext(),
                mainActivityInterface.getPreferences().getMyPreferenceInt(requireContext(), "lastUsedVersion", 0),
                mainActivityInterface.getVersionNumber().getVersionCode());
        if (!updateInfo) {
            myView.appUpdated.setVisibility(View.GONE);
        } else {
            myView.appUpdated.setVisibility(View.VISIBLE);
            myView.showUpdates.setText(mainActivityInterface.getVersionNumber().getFullVersionInfo());
            myView.showUpdates.setOnClickListener(b -> webLink("http://www.opensongapp.com/latest-updates"));

            // We've seen the warning, so update the preference
            mainActivityInterface.getPreferences().setMyPreferenceInt(requireContext(), "lastUsedVersion",
                    mainActivityInterface.getVersionNumber().getVersionCode());
        }

        // Check for backup status
        if (mainActivityInterface.getAlertChecks().showBackup(requireContext(),
                mainActivityInterface.getPreferences().getMyPreferenceInt(requireContext(),"runssincebackup",0))) {
            myView.timeToBackup.setVisibility(View.VISIBLE);
            String s = requireContext().getString(R.string.promptbackup).
                    replace("10","" +
                            mainActivityInterface.getPreferences().getMyPreferenceInt(requireContext(), "runssincebackup", 0));
            myView.backupDescription.setText(s);
            myView.backupNowButton.setOnClickListener(v -> {
                mainActivityInterface.navigateToFragment("opensongapp://settings/storage/backup",0);
                dismiss();
            });
        } else {
            myView.timeToBackup.setVisibility(View.GONE);
        }

        // Check for Google Play Service error
        if (mainActivityInterface.getAlertChecks().showPlayServicesAlert(requireContext())) {
            Log.d("StageMode", "onresume()  Play store isn't installed");
            myView.playServices.setVisibility(View.VISIBLE);
            myView.playServicesInfo.setOnClickListener(b -> webLink(getString(R.string.play_services_help)));
        } else {
            myView.playServices.setVisibility(View.GONE);
        }
    }

    private void webLink(String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        try {
            startActivity(i);
        } catch (Exception e) {
            Log.d("AlertDialogFragment", "Error showing webView");
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mainActivityInterface.refreshMenuItems();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myView = null;
    }
}
