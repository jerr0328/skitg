package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidGame extends AndroidApplication {
        public void onCreate (android.os.Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initialize(new Skitg(), false);
        }
}