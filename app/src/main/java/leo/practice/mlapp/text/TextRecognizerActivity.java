package leo.practice.mlapp.text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import leo.practice.mlapp.R;

public class TextRecognizerActivity extends AppCompatActivity {
    private static final String TAG = "TextRecognizerActivity";

    private Button captureButton, detectButton;
    private ImageView cameraImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognizer);

        // initialize all the components
        initialize();
    }

    private void initialize() {
        captureButton = findViewById(R.id.capture_button_id);
        detectButton = findViewById(R.id.detect_button_id);
        cameraImageView = findViewById(R.id.imageView);
    }
}
