package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView foto;
    private TextView about;
    TextView NamaLengkap;
    TextView email;
    TextView homepage;
    private Button btn_Click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        about = findViewById(R.id.label_about);
        NamaLengkap = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        homepage = findViewById(R.id.label_homepage);
        btn_Click = findViewById(R.id.button_homepage);
        foto = findViewById(R.id.image_profile);

        Bundle bundle = getIntent().getExtras();
        String labelAbout = bundle.getString("about");
        String labelFullname = bundle.getString("fullname");
        String labelEmail = bundle.getString("email");
        final String labelHomepage = bundle.getString("homepage");
        String uri = bundle.getString("image");

        foto.setImageURI(Uri.parse(uri));
        about.setText(labelAbout);
        NamaLengkap.setText(labelFullname);
        email.setText(labelEmail);
        homepage.setText(labelHomepage);

        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL(labelHomepage);
            }
        });
    }
    private void gotoURL(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}