package com.garethevans.church.opensongtablet.songmenu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.garethevans.church.opensongtablet.R;
import com.garethevans.church.opensongtablet.databinding.BottomSheetRandomSongBinding;
import com.garethevans.church.opensongtablet.interfaces.MainActivityInterface;
import com.garethevans.church.opensongtablet.songprocessing.Song;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Random;

public class RandomSongBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetRandomSongBinding myView;
    private MainActivityInterface mainActivityInterface;
    private final String whichMenu;
    private Song randomSong;

    public RandomSongBottomSheet() {
        // Default constructor required to avoid re-instantiation failures
        // Just close the bottom sheet
        whichMenu = "";
        dismiss();
    }

    public RandomSongBottomSheet(String whichMenu) {
        this.whichMenu = whichMenu;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivityInterface = (MainActivityInterface) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialog1 -> {
            FrameLayout bottomSheet = ((BottomSheetDialog) dialog1).findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = BottomSheetRandomSongBinding.inflate(inflater,container,false);

        if (getContext()!=null) {
            myView.dialogHeader.setText(getString(R.string.random_song));
        }
        myView.dialogHeader.setClose(this);

        // Get a random song
        findRandomSong();

        // Set up the listeners
        setupListeners();

        return myView.getRoot();
    }

    private void setupListeners() {
        myView.findRandom.setOnClickListener(v -> findRandomSong());
        myView.loadRandom.setOnClickListener(v -> doLoad());
        myView.currentRandomSong.setOnClickListener(v -> doLoad());
    }

    private void findRandomSong() {
        Random random = new Random();

        int randomNum;
        if (whichMenu.equals("song")) {
            randomNum = random.nextInt(mainActivityInterface.getSongsFound("song").size());
            randomSong = mainActivityInterface.getSongsFound("song").get(randomNum);
        } else if (!whichMenu.isEmpty()){
            randomNum = random.nextInt(mainActivityInterface.getSongsFound("set").size());
            randomSong = mainActivityInterface.getSongsFound("set").get(randomNum);
        }
        myView.currentRandomSong.setText(randomSong.getTitle());
        myView.currentRandomSong.setHint(randomSong.getFolder());
    }

    private void doLoad() {
        mainActivityInterface.doSongLoad(randomSong.getFolder(),randomSong.getFilename(),true);
        dismiss();
    }
}
