package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private ImageView changeimage;
    private TextInputEditText etnama,etemail,etpassword, confirm_inputpassword, ethomepage, etabout;
    private Button btnInput;
    private CircleImageView avatar, change_Avatar;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private Bitmap bitmap;
    private Uri imgUri = null;
    private boolean change_img = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeimage = (ImageView) findViewById(R.id.imageView);
        etnama = (TextInputEditText) findViewById(R.id.text_fullname);
        etemail = (TextInputEditText) findViewById(R.id.text_email);
        etpassword = (TextInputEditText) findViewById(R.id.text_password);
        confirm_inputpassword = (TextInputEditText) findViewById(R.id.text_confirm_password);
        ethomepage = (TextInputEditText) findViewById(R.id.text_homepage);
        etabout = (TextInputEditText) findViewById(R.id.text_about);
        btnInput = findViewById(R.id.button_ok);
        avatar = findViewById(R.id.image_profile);


        changeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(RegisterActivity.this, ProfileActivity.class);
                String fullname = etnama.getText().toString();
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
                String con_password = confirm_inputpassword.getText().toString();
                String homepage = ethomepage.getText().toString();
                String about = etabout.getText().toString();

                if (!change_img) {
                    Toast.makeText(RegisterActivity.this, "Foto Wajib Di Ubah", Toast.LENGTH_SHORT).show();
                } else if (fullname.isEmpty()) {
                    etnama.setError("Nama Wajib di Isi");
                } else if (email.isEmpty()) {
                    etemail.setError("Email Wajib Di Isi");
                } else if (password.isEmpty()) {
                    etpassword.setError("Password Wajib Di Isi");
                } else if (con_password.isEmpty()) {
                    confirm_inputpassword.setError("Confirm Password Wajib Di Isi");
                } else if (homepage.isEmpty()) {
                    ethomepage.setError("Homepage Wajib Di Isi");
                } else if (about.isEmpty()) {
                    etabout.setError("About Wajib DI isi");
                } else if (!password.equals(con_password)) {
                    Toast.makeText(RegisterActivity.this, "Confirm password tidak cocok", Toast.LENGTH_SHORT).show();
                } else {
                    String image = imgUri.toString();
                    pindah.putExtra("image", image);
                    pindah.putExtra("fullname", fullname);
                    pindah.putExtra("email", email);
                    pindah.putExtra("password", password);
                    pindah.putExtra("con_password", con_password);
                    pindah.putExtra("homepage", homepage);
                    pindah.putExtra("about", about);
                    startActivity(pindah);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Batal Mengunggah Foto", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    try {
                        change_img = true;
                        imgUri = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                        avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Tidak dapat mengunggah foto", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }
}