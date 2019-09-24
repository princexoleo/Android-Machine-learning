package leo.practice.mlapp.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

import leo.practice.mlapp.R;

public class TextRecognizerActivity extends AppCompatActivity {
    private static final String TAG = "TextRecognizerActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button captureButton, detectButton;
    private ImageView imageView;
    private TextView resultsTextView;

    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognizer);

        // initialize all the components
        initialize();

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // text detection and show
                detectText();

            }
        });
    }

    private void detectText() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

        Task<FirebaseVisionText> result =
                textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        // now process the text
                        processText(firebaseVisionText);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void processText(FirebaseVisionText firebaseVisionText) {
        // collects all the block and save it in a list
        List<FirebaseVisionText.TextBlock> textBlocks = firebaseVisionText.getTextBlocks();

        if (textBlocks.size() == 0){
            Toast.makeText(this, "There is no text!", Toast.LENGTH_SHORT).show();
            return;
        }
        // iterate every blocks
        for (FirebaseVisionText.TextBlock block: firebaseVisionText.getTextBlocks()){
            String text = block.getText();
            // now set the text to our TextView
            resultsTextView.setTextSize(24);
            resultsTextView.setText(text);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = null;
            if (extras != null) {
                imageBitmap = (Bitmap) extras.get("data");
            }
            imageView.setImageBitmap(imageBitmap);
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    private void initialize() {
        captureButton = findViewById(R.id.capture_button_id);
        detectButton = findViewById(R.id.detect_button_id);
        imageView = findViewById(R.id.imageView);
        resultsTextView = findViewById(R.id.result_textView_id);
    }
}
