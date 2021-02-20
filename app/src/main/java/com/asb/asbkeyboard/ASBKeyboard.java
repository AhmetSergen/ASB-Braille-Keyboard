/* ASB */
package com.asb.asbkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

// DESCRIPTION :
// Braille dot code :
//  1   4
//  2   5
//  3   6
// The basic braille alphabet, braille numbers, braille punctuation and special symbols characters are constructed from six dots.
// These braille dots are positioned like the figure six on a die, in a grid of two parallel vertical lines of three dots each.
// From the six dots that make up the basic grid, 2^6 = 64 different configurations can be created.
// These 64 braille characters can be represented as 6 digit numbers like 1=a, 1345=n, 13456=y, 1356=z

public class ASBKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private KeyboardView keyboardView;
    private Keyboard keyboard;

    static int commitCodeDS = 10;                               // Default delay for current braille code commitment delay in deciseconds.
    final long[] vibrationPattern100ms = {0,100};               // {a,b} : Sleep for a milliseconds, vibrate for b milliseconds
    final long[] vibrationPattern10ms = {0,10};                 // {a,b} : Sleep for a milliseconds, vibrate for b milliseconds

    private boolean singleCaps = false;                         // Caps for single letter
    private boolean wordCaps = false;                           // Caps lock for entire word
    private boolean numberIndicator = false;                    // Boolean value for number indicator
    private boolean options = false;                            // Boolean value for options section. If options indicator code(123456) is typed, options value sets to true.
    private boolean vibration = true;                           // Boolean value for vibration option
    private boolean speech = true;                              // Boolean value for speech option

    static int touchCount = 0;                                  // Total touch counts for current braille code until commitment
    static int timerCount = 0;                                  // Timer countdown for current braille code commitment

    static int[] currentCode = {0,0,0,0,0,0};                   // Represents current braille code before commitment. Each digit represent a position in single braille code corresponding to its index value.
    // a="100000", b="120000", c="100400", d="100450"
    // After commitment, there 0's will be removed from string and all its left will be decimal code representation of a single braille code (for example d="145"), which are listed in arrays above;

    static int[] pressedButtonsStackArray = new int[12];        // Array that where touched position coordinates are stored until all pointers (fingers) are lifted up.
    // pressedButtonsStackArray[touchID]

    private TextToSpeech textToSpeech;

    // List that contains; braille code(column 0) representations of ascii codes for lowercase characters(column 1) .
    // Letters can be selected by default value or after using letter indicator(braille code=56)
    // row -> {braille dot code, lowercase char ascii code equivalent}
    // {Braille Code,     ASCII Code}
    static String[][] brailleAlphabet = {
            {"1",       "97"},  // a
            {"12",      "98"},  // b
            {"14",      "99"},  // c
            {"16",      "231"}, // ç
            {"145",     "100"}, // d
            {"15",      "101"}, // e
            {"124",     "102"}, // f
            {"1245",    "103"}, // g
            {"126",     "287"}, // ğ
            {"125",     "104"}, // h
            {"35",      "141"}, // ı
            {"24",      "105"}, // i
            {"245",     "106"}, // j
            {"13",      "107"}, // k
            {"123",     "108"}, // l
            {"134",     "109"}, // m
            {"1345",    "110"}, // n
            {"135",     "111"}, // o
            {"246",     "246"}, // ö
            {"1234",    "112"}, // p
            {"12345",   "113"}, // q
            {"1235",    "114"}, // r
            {"234",     "115"}, // s
            {"146",     "351"}, // ş
            {"2345",    "116"}, // t
            {"136",     "117"}, // u
            {"1236",    "118"}, // v
            {"2456",    "119"}, // w
            {"1346",    "120"}, // x
            {"13456",   "121"}, // y
            {"1356",    "122"}, // z

            // Some punctuation signs can be selected by default single braille codes (like ?=236)
            // This list contains punctuation signs that can be selected via default single braille code
            {"2",       "44"},  // ,
            {"23",      "59"},  // ;
            {"25",      "58"},  // :
            {"256",     "46"},  // .
            {"236",     "63"},  // ?
            {"235",     "33"},  // !
            {"3",       "39"},  // '
            {"36",      "45"},  // -
    };
    // List that contains; braille code(column 0) representations of braille code for numbers(column 1), after number indicator (braille code=3456).
    // Numbers can be selected by using number indicator(braille code=3456) before desired number.
    static String[][] brailleNumbers = {
            {"1",       "49"}, // 1
            {"12",      "50"}, // 2
            {"14",      "51"}, // 3
            {"145",     "52"}, // 4
            {"15",      "53"}, // 5
            {"124",     "54"}, // 6
            {"1245",    "55"}, // 7
            {"125",     "56"}, // 8
            {"24",      "57"}, // 9
            {"245",     "48"}, // 0
    };

    // Some punctuation signs can be selected by default single braille codes (like ?=236)
    // Some other punctuation signs can be selected by using different indicators (like 3,5,456).
    static String[][] braillePunctuations3 = { // this punctuation can be selected after "3" indicator
            {"2356",    "34"},  // "
    };
    static String[][] braillePunctuations5 = {
            {"126",     "40"},  // (
            {"345",     "41"},  // )
    };
    static String[][] braillePunctuations456 = {
            {"34",      "47"},  // /
            {"16",      "92"},  // \
    };

    // Indicators:
    // Number Indicator = "3456"
    // Single Caps = "6"
    // Word Caps = Single Caps + "6"
    // Keyboard Options (Open-Close) = "123456"

    private String voiceOn;
    private String voiceOff;
    private String voiceSingleCaps;
    private String voiceWordCaps;
    private String voiceNumberIndicator;
    private String voiceOptionsMenu;
    private String voiceVoiceover;
    private String voiceTypingDelay;
    private String voiceVibration;
    private String voiceDelete;
    private String voiceSpace;
    private String voiceEnter;



    public int getLength(int number){                                                               // Returns Number of digits of a number
        int length = 0;
        long temp = 1;
        while (temp <= number) {
            length++;
            temp *= 10;
        }
        return length;
    }

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(this,R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    Log.i("Device_Language",Locale.getDefault().getCountry());

                    if(Locale.getDefault().getCountry().equals("TR")){
                        Locale localeTR = new Locale("tr", "TR");
                        textToSpeech.setLanguage(localeTR);
                        voiceOn = "Açık";
                        voiceOff = "Kapalı";
                        voiceSingleCaps = "Büyük harf";
                        voiceWordCaps = "Büyük harf kelime";
                        voiceNumberIndicator = "Numara belirteçi";
                        voiceOptionsMenu = "Ayarlar menüsü ";
                        voiceVoiceover = "Seslendirme ";
                        voiceTypingDelay = "Yazma gecikmesi ";
                        voiceVibration = "Titreşim ";
                        voiceDelete = "Silindi";
                        voiceSpace = "Boşluk";
                        voiceEnter = "Giriş";
                    }
                    else{
                        textToSpeech.setLanguage(Locale.US);
                        //textToSpeech.setLanguage(Locale.UK);
                        voiceOn = "On";
                        voiceOff = "Off";
                        voiceSingleCaps = "Single caps ";
                        voiceWordCaps = "Word caps ";
                        voiceNumberIndicator = "Number indicator";
                        voiceOptionsMenu = "Options menu ";
                        voiceVoiceover = "Voice over ";
                        voiceTypingDelay = "Typing delay ";
                        voiceVibration = "Vibration";
                        voiceDelete = "Delete";
                        voiceSpace = "Space";
                        voiceEnter = "Enter";

                    }
                }
            }
        });

        return keyboardView;
    }

    private void playClick(int i) { // Play sound effects for buttons
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(i)
        {
            case 2:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 1:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case 3:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }

    }

    @Override
    public void onPress(int i) {
        //Log.i("public void","onPress");
    }

    @Override
    public void onRelease(int i) {
        //Log.i("public void","onRelease");
    }

    @Override
    public void onKey(int key_code, int[] ints) {
        //key_code = android.codes (in xml)
        //Log.i("options",""+options);
        //Log.i("vibration",""+vibration);
        final InputConnection ic = getCurrentInputConnection();
        //playClick(key_code);
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (touchCount < 6){                                                               // max touch count is 6
            //Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibration==true) {
                vibrator.vibrate(vibrationPattern10ms, -1); // Repeat: 0=forever, -1=not repeat
            }
            pressedButtonsStackArray[touchCount]= key_code;
        }
        if (touchCount ==0) { // If its first touch, start timer for braille code commitment. Selected braille code will be committed after waiting idle(not touching any other button) for specified time.
            final Timer myTimer=new Timer();
            TimerTask task =new TimerTask() {
                @Override
                public void run() {
                    // empty stack
                    timerCount++;   // 1 timer_count = 1 period in schedule
                    //Log.i("Timer",""+ timerCount);
                    if(timerCount == commitCodeDS) {                                     // Countdown for commitment.

                        // Commitment Process Start_______________________________________
                        for (int index = 0; index < pressedButtonsStackArray.length-1 ; index++){
                            if (pressedButtonsStackArray[index] == 0){
                                break;
                            }
                            int length_of_array_item = getLength(pressedButtonsStackArray[index] );
                            if ( length_of_array_item == 1 ){                               // If pressed button of current index is belongs to main area (1|2|3|4|5|6)
                                currentCode[pressedButtonsStackArray[index] - 1 ] = pressedButtonsStackArray[index];
                            }
                            else{                                                           // If pressed button of current index is a cross area (12|14|145 ...)
                                int number = pressedButtonsStackArray[index];
                                if ( (currentCode[(number%10)-1] == 0) ) {
                                    currentCode[(number % 10) - 1] = 8;                    // 8 means this digit of current code could be selected or not, and it has a lower possibility if its selected.
                                }                                                           // which means this button has a lower priority while deciding currently typed braille code
                                number = number / 10;
                                if ( (currentCode[number-1] == 0) ) {
                                    currentCode[(number % 10) - 1] = 7;                    // 7 means this digit of current code could be selected or not, and it has a higher possibility if its selected.
                                }                                                           // which means this button has a higher priority while deciding currently typed braille code
                            }
                        }

                        // Process 7 s
                        for (int i=0; i<6 ; i++){
                            if (touchCount ==0) {
                                currentCode[i] = 0;
                            }else{
                                if (currentCode[i] > 0 & currentCode[i] < 8) {
                                    if (currentCode[i] == 7) {
                                        currentCode[i] = i+1;
                                    }
                                    touchCount--;
                                }
                            }
                        }
                        // Process 8 s
                        for (int i=0; i<6 ; i++){
                            if (touchCount ==0 & currentCode[i]==8 ) {
                                currentCode[i] = 0;
                            }else{
                                if (currentCode[i] > 0) {
                                    if (currentCode[i] == 8) {
                                        currentCode[i] = i+1;
                                        touchCount--;
                                    }
                                }
                            }
                        }

                        // Translate current braille code to string. For comparison in list
                        String currentCodeSTR = "";
                        for (int i=0;i<6;i++){
                            if(currentCode[i]!=0){
                                currentCodeSTR = currentCodeSTR+ currentCode[i];
                            }
                        }

                        // Find if current code string presents in any array. If it exists, then get the ascii code of that character and commit that character
                        if (currentCodeSTR.equals("6") & options==false){                 // If current braille code is capitalize letter
                            //Log.i("caps","if");
                            if (singleCaps ==true){                                         // If its second capitalize letter, it should capitalize entire word until next space.
                                wordCaps =true;
                                numberIndicator = false;
                                textToSpeech.speak(""+ voiceWordCaps, TextToSpeech.QUEUE_FLUSH, null);
                                Log.i("wordCaps", ""+wordCaps);
                            }else {
                                singleCaps = true;
                                numberIndicator = false;
                                textToSpeech.speak(""+ voiceSingleCaps, TextToSpeech.QUEUE_FLUSH, null);
                                Log.i("singleCaps", ""+singleCaps);
                            }
                        }else if(currentCodeSTR.equals("3456") & options==false){         // If current braille code is number indicator
                            numberIndicator = true;
                            singleCaps = false;
                            wordCaps = false;
                            textToSpeech.speak(""+ voiceNumberIndicator, TextToSpeech.QUEUE_FLUSH, null);
                            Log.i("number_indicator",""+ numberIndicator);
                        }else if(currentCodeSTR.equals("123456")){                        // If current braille code is options menu indicator
                            numberIndicator = false;
                            singleCaps = false;
                            wordCaps = false;
                            if (options==false) {
                                options = true;
                                textToSpeech.speak(""+ voiceOptionsMenu + voiceOn, TextToSpeech.QUEUE_FLUSH, null);
                            }else if (options==true){
                                options = false;
                                textToSpeech.speak(""+ voiceOptionsMenu + voiceOff, TextToSpeech.QUEUE_FLUSH, null);
                            }
                            Log.i("options",""+options);
                        }else{                                                              // If currently typed braille code is not an indicator
                            if (numberIndicator ==true){                                    // If number indicator is open
                                for (int i = 0; i < 10; i++) {
                                    if (currentCodeSTR.equals(brailleNumbers[i][0])) {
                                        // Commit current braille code
                                        //Log.i("number indicator", "if");
                                        char code = (char) Integer.parseInt(brailleNumbers[i][1]);
                                        if(speech==true) {
                                            textToSpeech.speak("" + code, TextToSpeech.QUEUE_FLUSH, null);
                                        }
                                        ic.commitText(String.valueOf(code), 1);  // Commit
                                        break;
                                    }
                                }
                            }else if(options==true){                                        // If options indicator is open
                                switch (currentCodeSTR){
                                    case "4":
                                        speech = true;
                                        textToSpeech.speak(""+ voiceVoiceover + voiceOn, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                    case "1":
                                        speech = false;
                                        textToSpeech.speak(""+ voiceVoiceover + voiceOff, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                    case "5":                                               // Increase commitment delay
                                        if(commitCodeDS < 15){
                                            commitCodeDS++;
                                        }
                                        textToSpeech.speak(""+ voiceTypingDelay + commitCodeDS, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                    case "2":                                               // Decrease commitment delay
                                        if(commitCodeDS > 2){
                                            commitCodeDS--;
                                        }
                                        textToSpeech.speak(""+ voiceTypingDelay + commitCodeDS, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                    case "6":
                                        vibration = true;
                                        textToSpeech.speak(""+ voiceVibration + voiceOn, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                    case "3":
                                        vibration = false;
                                        textToSpeech.speak(""+ voiceVibration + voiceOff, TextToSpeech.QUEUE_FLUSH, null);
                                        break;
                                }
                                Log.i("Speech",""+vibration);
                                Log.i("Commitment speed",""+ commitCodeDS);
                                Log.i("vibration",""+vibration);
                            }
                            else {                                                          // else
                                for (int i = 0; i < 39; i++) {
                                    if (currentCodeSTR.equals(brailleAlphabet[i][0])) {
                                        // Commit current braille code
                                        char code = (char) Integer.parseInt(brailleAlphabet[i][1]);
                                        if (Character.isLetter(code) && (singleCaps | wordCaps)) {  // If caps lock is open, convert to upper case
                                            code = Character.toUpperCase(code);
                                            singleCaps = false;
                                        }
                                        if(speech==true) {
                                            textToSpeech.speak("" + code, TextToSpeech.QUEUE_FLUSH, null);
                                        }
                                        singleCaps = false;
                                        ic.commitText(String.valueOf(code), 1);  // Commit
                                        break;
                                    }
                                }
                            }
                        }
                        // Commitment Process End_______________________________________

                        Log.i("pressedButtonsStackArray", "[0]="+pressedButtonsStackArray[0]+",[1]="+pressedButtonsStackArray[1]+",[2]="+pressedButtonsStackArray[2]+",[3]="+pressedButtonsStackArray[3]+",[4]="+pressedButtonsStackArray[4]+",[5]="+pressedButtonsStackArray[5]);
                        Log.i("currentCode",""+currentCodeSTR);
                        if (vibration == true) {
                            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(vibrationPattern100ms, -1); // Repeat: 0=forever, -1=not repeat
                        }
                        touchCount = 0;
                        timerCount = 0;
                        for (int i=0 ; i<6 ; i++){
                            currentCode[i] = 0;
                        }
                        for (int i=0 ; i<12 ; i++){
                            pressedButtonsStackArray[i] = 0;
                        }
                        myTimer.cancel();
                    }
                }
            };
            myTimer.schedule(task, 0, 100); // Call every 100 ms
        }
        else {                                                                              // When another button is clicked
            timerCount = 0;                                                                // When another button is clicked, timer resets.
            //Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibration == true) {
                vibrator.vibrate(vibrationPattern10ms, -1); // Repeat: 0=forever, -1=not repeat
            }
        }
        touchCount++;
        Log.i("Touch count:", "" + touchCount);
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {   // Delete
        Log.i("Swipe","left");
        final InputConnection ic = getCurrentInputConnection();
        //playClick(key_code);
        ic.deleteSurroundingText(1,0);
        //playClick(12); // Sound
        if (speech==true) {
            textToSpeech.speak(""+ voiceDelete, TextToSpeech.QUEUE_FLUSH, null);
        }
        if (vibration==true) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(vibrationPattern100ms, -1); // Repeat: 0=forever, -1=not repeat
        }
    }

    @Override
    public void swipeRight() {  // Space
        Log.i("Swipe","right");
        final InputConnection ic = getCurrentInputConnection();
        //playClick(key_code);
        char code = (char)32;   // Space code
        singleCaps = false;
        wordCaps = false;
        numberIndicator = false;
        ic.commitText(String.valueOf(code), 1);  // Commit
        if (speech==true) {
            textToSpeech.speak(""+ voiceSpace, TextToSpeech.QUEUE_FLUSH, null);
        }
        if (vibration==true) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(vibrationPattern100ms, -1); // Repeat: 0=forever, -1=not repeat
        }
    }

    @Override
    public void swipeDown() {   // Close keyboard
        Log.i("Swipe","down");

        if (vibration==true) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(vibrationPattern100ms, -1); // Repeat: 0=forever, -1=not repeat
        }

        super.hideWindow(); // Hide virtual keyboard
    }

    @Override
    public void swipeUp() { // New line
        Log.i("Swipe","up");
        final InputConnection ic = getCurrentInputConnection();
        wordCaps =false;
        singleCaps =true;
        numberIndicator = false;
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
        if (speech = true) {
            textToSpeech.speak(""+ voiceEnter, TextToSpeech.QUEUE_FLUSH, null);
        }
        if (vibration==true) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(vibrationPattern100ms, -1); // Repeat: 0=forever, -1=not repeat
        }
    }
}