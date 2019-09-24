package leo.practice.mlapp;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.annotation.Annotation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import leo.practice.mlapp.barcode.BarcodeScannerActivity;
import leo.practice.mlapp.face.FaceDetectActivity;
import leo.practice.mlapp.text.TextRecognizerActivity;

public class MainActivity extends AppCompatActivity {

    private Button facedetectButton;
    private Button barcodeScanButton;
    private Button textRecognizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        // set button click operation
        textRecognizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make intent from this activity to textRecognize activity
                Intent intent = new Intent(MainActivity.this, TextRecognizerActivity.class);
                startActivity(intent);
            }
        });

        facedetectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make intent from this activity to faceDetect activity
                Intent intent = new Intent(MainActivity.this, FaceDetectActivity.class);
                startActivity(intent);

            }
        });

        barcodeScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make intent from this activity to BarcodeScanner activity
                Intent intent = new Intent(MainActivity.this, BarcodeScannerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        // initialize all the components
        facedetectButton = findViewById(R.id.face_detect_button_id);
        barcodeScanButton = findViewById(R.id.scan_barcode_button_id);
        textRecognizeButton = findViewById(R.id.text_reco_button_id);
    }
}
