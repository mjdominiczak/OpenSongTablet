package com.garethevans.church.opensongtablet.preferences;

// This class is used to store the static variables the app needs
// Most used to be in FullscreenActivity, but have been moved here
// Many have been removed as they can just be loaded from preferences or objects as required
// These are the ones that are frequently accessed, so easier just to be static

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;

import com.garethevans.church.opensongtablet.performance.PerformanceFragment;

import java.util.ArrayList;

// TODO REMOVE THIS ABOMINATION!!!
public class StaticVariables {



















    // CHECK IF I NEED THESE

    // The song fields
    public static String mEncoding = "UTF-8", mExtraStuff1 = "", mExtraStuff2 = "";

    // For moving through songs in list with swipe
    public static ArrayList<String> songsInList = new ArrayList<>();

    public static PerformanceFragment performanceFragment;

    // Set stuff
    public static String previousSongInSet = "";
    public static String nextSongInSet = "";
    public static String myNewXML;
    public static boolean homeFragment;
    public static boolean sortAlphabetically = true;
    public static String whichDirection = "R2L";
    public static String whattodo = "";
    public static int pdfPageCurrent = 0;
    public static int pdfPageCount = 0;
    public static String linkclicked = "";
    public static int currentSongIndex;
    public static int previousSongIndex;
    public static int nextSongIndex;
    // Views and bits on the pages
    public static int currentScreenOrientation;
    public static boolean orientationChanged;
    public static boolean indexRequired = true;
    public static boolean indexComplete = false;
    public static boolean needtorefreshsongmenu;
    public static String mBluetoothName;
    public static boolean isSong;
    public static String myXML;
    static String nextSongKeyInSet = "";
    public static String whatsongforsetwork = "";
    public static String newSetContents = "";
    public static String settoload = "";
    public static String setMoveDirection = "";
    public static String setnamechosen = "";
    public static String[] mSet;
    public static String[] mSetList;
    public static boolean setView;
    public static boolean doneshuffle = false;
    public static boolean setchanged = false;
    public static int setSize;
    public static int indexSongInSet;
    public static ArrayList<String> mTempSetList;
    public static String currentSet;

    
    // Storage variables
    static Uri uriTree;



    // Default text sizes
    static final float infoBarLargeTextSize = 20.0f;
    static final float infoBarSmallTextSize = 14.0f;

    // Song locations.  These are also saved as preferences if the song loads correctly (without crashing midway)
    public static String whichSongFolder = "";
    public static String songfilename = "";

    // The action bar size (used to work out song space available)
    public static int ab_height;

    // The song secions.  Used when paring songs into bits
    public static String[] songSections;
    public static int currentSection;



    // Song scaling used for scaling overrides if appropriate (matches to user preference)
    static String thisSongScale = "W";

    // The theme
    public static String mDisplayTheme = "dark";




    // Option menu defines which menu we are in
    static String whichOptionMenu = "MAIN";


    // Stuff for transposing songs
    public static int detectedChordFormat = 1;
    public static int transposeTimes = 1;
    public static String transposeDirection = "0";
    public static String transposedLyrics = "";

    // The toast message - sometimes used to identify next steps for the app, sometimes just to display
    public static String myToastMessage = "";

    // Uri of external files (image from image file) clicked to present
    static Uri uriToLoad;

    // Metronome stuff
    static boolean metronomeok;
    static boolean mTimeSigValid = false;
    static boolean usingdefaults = false;
    public static boolean clickedOnMetronomeStart = false;
    public static String whichbeat = "a";
    public static String metronomeonoff = "off";
    public static short noteValue = 4, beats = 4;;




    //Pad stuff
    public static MediaPlayer mPlayer1 = new MediaPlayer();
    public static MediaPlayer mPlayer2 = new MediaPlayer();

    public static boolean pad1Playing;
    public static boolean pad2Playing;
    static boolean pad1Fading = false;
    static boolean pad2Fading = false;
    static boolean clickedOnPadStart = false;
    static int audiolength = -1;
    static int padtime_length = 0;
    static int autoscroll_modifier = 0;
    static int padInQuickFade = 0;
    static float pad1FadeVolume;
    static float pad2FadeVolume;
    public static String pad_filename = "null";



    //Autoscroll stuff
    public static boolean autoscrollok;
    public static boolean clickedOnAutoScrollStart = false;
    static boolean pauseautoscroll = true;
    static boolean autoscrollispaused = false;
    public static boolean isautoscrolling = false;
    public static boolean wasscrolling;
    static boolean learnPreDelay = false;
    static boolean learnSongLength = false;
    static final int autoscroll_pause_time = 500;
    static int autoScrollDelay;
    static int autoScrollDuration;
    static int scrollpageHeight;
    static int total_pixels_to_scroll = 0;


    // Pedal movement
    static boolean pedalPreviousAndNextNeedsConfirm = true;
    static boolean pedalPreviousAndNextIgnore = false;
    static boolean reloadOfSong = false;
    static boolean showCapoInChordsFragment = false;
    static boolean ignoreGesture = false;
    static boolean canGoToNext = false;
    static boolean canGoToPrevious = false;
    static boolean doVibrateActive = true;

    // PDF stuff
    public static boolean showstartofpdf = true;

    // Used for the chord image display
    static String allchords = "";
    public static String chordnotes = "";
    public static String temptranspChords = "";

    // Request codes for callbacks
    public static final int REQUEST_CAMERA_CODE = 1973;
    static final int REQUEST_MICROPHONE_CODE = 1974;
    static final int REQUEST_PDF_CODE = 1975;
    static final int LINK_AUDIO = 1000;
    static final int LINK_OTHER = 1001;
    static final int REQUEST_IMAGE_CODE = 1111;
    public static final int REQUEST_BACKGROUND_IMAGE1 = 1544;
    public static final int REQUEST_BACKGROUND_IMAGE2 = 1555;
    public static final int REQUEST_BACKGROUND_VIDEO1 = 1556;
    public static final int REQUEST_BACKGROUND_VIDEO2 = 1557;
    public static final int REQUEST_CUSTOM_LOGO = 1558;
    static final int REQUEST_PROFILE_LOAD = 4567;
    static final int REQUEST_PROFILE_SAVE = 5678;

    public static Activity activity;

}
