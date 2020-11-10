package com.example.provam2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provam2.data_structures.Game;

import java.io.File;

public class See_Teams extends AppCompatActivity {

    private String full_name = "";
    private String username = "";
    private String position = "";
    private String team = "";
    private String photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeteams);

        Bundle extra = getIntent().getExtras();

        if( extra != null ){

            full_name = extra.getString( "full_name" );
            username = extra.getString( "username" );
            position = extra.getString( "position" );
            team = extra.getString( "team" );
            photo = extra.getString( "photo" );

        }

        Log.d("full_name", "onCreate: " + full_name);
        Log.d("username", "onCreate: " + username);
        Log.d("position", "onCreate: " + position);
        Log.d("team", "onCreate: " + team);
        Log.d("photo", "onCreate: " + photo);

        TextView fullname_obj = findViewById(R.id.fullname_view);
        TextView username_obj = findViewById(R.id.username_view);
        TextView position_obj = findViewById(R.id.position_view);
        TextView team_obj     = findViewById(R.id.team_view    );

        fullname_obj.setText( full_name );
        username_obj.setText( username );
        position_obj.setText( position );
        team_obj    .setText( team );

        File image = new File( photo );
        Bitmap myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
        ImageView imageobj = findViewById( R.id.imageView );
        imageobj.setImageBitmap( myBitmap );

    }

    public void exit(View view) {
        finish();
    }
}