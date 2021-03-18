package Operation_Nightwatcher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.td.OperationNightwatcher.BuildConfig;
import com.td.OperationNightwatcher.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

//the "sign in" class that displays before you get put into the game
public class Activity_SignIn extends AppCompatActivity {
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int CHOOSE_PIC_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;

    private Uri mMediaUri;
    private ImageView img_profile;
    Bitmap selectedImage;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        // Initialize textView and button
        EditText userName = findViewById(R.id.userName);
        Button okButton = findViewById(R.id.okButton);
        ImageView img_plus = findViewById(R.id.img_plus);
        img_profile = findViewById(R.id.img_profile);

        // Attach the click listener to the buttons
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_SignIn.this, Activity_Game.class);
                if(selectedImage != null){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    intent.putExtra("profileImage",bytes);
                }
                startActivity(intent);
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Change Pic Pressed", Toast.LENGTH_SHORT).show();

                //show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_SignIn.this);
                builder.setTitle("Upload or Take a photo");
                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //upload image
                        Intent choosePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        choosePictureIntent.setType("image/*");
                        startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE);
                    }
                });

                builder.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //take photo
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                        if (mMediaUri == null) {
                            //display error
                            Toast.makeText(getApplicationContext(), "Sorry there was an error! Try again.", Toast.LENGTH_LONG).show();
                        } else {
                            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                            takePicture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && data.getData()!=null) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                img_profile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Sorry there was an error! Try again.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image.", Toast.LENGTH_LONG).show();
        }
    }

    private Uri getOutputMediaFileUri(int mediaTypeImage) {
        if (isExternalStorageAvailable()) {
            //get the URI, external storage dir
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "UPLOADIMAGES");
            //create subdirectore if it does not exist
            if (!mediaStorageDir.exists()) {
                //create dir
                if (!mediaStorageDir.mkdirs()) {
                    return null;
                }
            }
            //create a file name
            //create file
            File mediaFile = null;
            Date now = new Date();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(now);

            String path = mediaStorageDir.getPath() + File.separator;
            if (mediaTypeImage == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(path + "IMG_" + timestamp + ".jpg");
            }
            //return file uri
            Log.d("UPLOADIMAGE", "FILE: " + Uri.fromFile(mediaFile));
            return FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                    BuildConfig.APPLICATION_ID + ".provider", mediaFile);
        } else {
            return null;
        }
    }

    //check if external storage is mounted. helper method
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onRestart(){ super.onRestart(); }
}
