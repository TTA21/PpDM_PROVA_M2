package com.example.provam2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provam2.data_structures.Game;
import com.example.provam2.data_structures.Teams;
import com.example.provam2.data_structures.Users;
import com.example.provam2.utils.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class register_class extends AppCompatActivity {

    private Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    private String photo_path = "";

    public void insert(View view) {

        TextView fullname_obj = findViewById( R.id.full_name );
        TextView username_obj = findViewById( R.id.username );
        TextView position_obj = findViewById( R.id.position );
        TextView team_obj     = findViewById( R.id.team );

        String fullname = fullname_obj.getText().toString();
        String username = username_obj.getText().toString();
        String position = position_obj.getText().toString();
        String team     = team_obj    .getText().toString();

        if( !(fullname.isEmpty() || username.isEmpty() || position.isEmpty() || team.isEmpty()) ){

            if( game.getGame() == null ){   ///First array object

                Users new_user = new Users();
                new_user.setFull_name( fullname );
                new_user.setUsername( username );
                new_user.setPosition( position );
                new_user.setTeam( team );
                new_user.setPhoto( photo_path );

                Teams new_team = new Teams();
                new_team.setTeam( new ArrayList<Users>() );
                new_team.getTeam().add(new_user);

                game.setGame( new ArrayList<Teams>());
                game.getGame().add( new_team );         ////First Team created

            }else{                          ///There are more objects

                boolean inserted = false;   ///if ture, user inserted
                boolean name_in_use = false;
                boolean username_in_use = false;

                for( int t_game = 0 ; t_game < game.getGame().size() ; t_game++ ){                              ///For each team
                    for( int t_team = 0 ; t_team < game.getGame().get(t_game).getTeam().size() ; t_team++ ){    ///for each user

                        String user_fullname = game.getGame().get(t_game).getTeam().get(t_team).getFull_name();
                        String user_username = game.getGame().get(t_game).getTeam().get(t_team).getUsername();
                        String user_position = game.getGame().get(t_game).getTeam().get(t_team).getPosition();
                        String user_team     = game.getGame().get(t_game).getTeam().get(t_team).getTeam();
                        String user_photo    = game.getGame().get(t_game).getTeam().get(t_team).getPhoto();


                        if( user_fullname.equals( fullname ) ){ ///2 equal names
                            Toast.makeText( this , "Nome já cadastrado" , Toast.LENGTH_SHORT );
                            name_in_use = true;
                            Log.d("WARNING", "Nome já cadastrado");
                        }else {
                            if( user_username.equals( username ) ){ ///2 equal usernames
                                Toast.makeText( this , "Nome de usuario já cadastrado" , Toast.LENGTH_SHORT );
                                username_in_use = true;
                                Log.d("WARNING", "Nome de usuario já cadastrado");
                            }else{  ///Se as credencias forem validas, cadastrar novo usuario

                                if( user_team.equals( team ) ){
                                    Users new_user = new Users();
                                    new_user.setFull_name( fullname );
                                    new_user.setUsername( username );
                                    new_user.setPosition( position );
                                    new_user.setTeam( team );
                                    new_user.setPhoto( photo_path );
                                    game.getGame().get(t_game).getTeam().add(new_user);
                                    Log.d("Team member added" , "insert: ");
                                    inserted = true;
                                }

                            }
                        }

                        if( inserted ){
                            break;
                        }

                    }

                    if( inserted ){
                        break;
                    }
                }

                if(!inserted && !name_in_use && !username_in_use){

                    Users new_user = new Users();
                    new_user.setFull_name( fullname );
                    new_user.setUsername( username );
                    new_user.setPosition( position );
                    new_user.setTeam( team );
                    new_user.setPhoto( photo_path );

                    Teams new_team = new Teams();
                    new_team.setTeam( new ArrayList<Users>() );
                    new_team.getTeam().add(new_user);

                    game.getGame().add( new_team );
                    inserted = true;
                    Log.d("Creating new Game Team" , "insert: ");
                }

            }

            display_object();
            update_listView();

        }


    }

    void display_object(){

        Log.d("NEW LINE", "-------------");
        Log.d("----------", "-------------");

        for( int t_game = 0 ; t_game < game.getGame().size() ; t_game++ ) {                              ///For each team
            for (int t_team = 0; t_team < game.getGame().get(t_game).getTeam().size(); t_team++) {    ///for each user

                Log.d("//////////////", "///////////////");

                Log.d("Game[" + t_game + "]" , "asd");
                Log.d("Team[" + t_team + "]" , "asd");

                String user_fullname = game.getGame().get(t_game).getTeam().get(t_team).getFull_name();
                String user_username = game.getGame().get(t_game).getTeam().get(t_team).getUsername();
                String user_position = game.getGame().get(t_game).getTeam().get(t_team).getPosition();
                String user_team     = game.getGame().get(t_game).getTeam().get(t_team).getTeam();
                String user_photo    = game.getGame().get(t_game).getTeam().get(t_team).getPhoto();

                Log.d("user_fullname", "display_object: " + user_fullname);
                Log.d("user_username", "display_object: " + user_username);
                Log.d("user_position", "display_object: " + user_position);
                Log.d("user_team", "display_object: " + user_team);
                Log.d("user_photo", "display_object: " + user_photo);

                Log.d("//////////////", "///////////////");

            }
        }

        Log.d("----------", "-------------");

    }

    ListView listView;
    private ArrayList<String> listView_Array = new ArrayList<String>();
    private ArrayList<String> listView_Index = new ArrayList<String>();
    private String current_team = "";
    private String old_team = "";

    void update_listView(){

        listView_Array = new ArrayList<String>();
        listView_Index = new ArrayList<String>();
        current_team = "";
        old_team = "";

        for( int t_game = 0 ; t_game < game.getGame().size() ; t_game++ ) {                              ///For each team
            for (int t_team = 0; t_team < game.getGame().get(t_game).getTeam().size(); t_team++) {    ///for each user

                current_team = game.getGame().get(t_game).getTeam().get(t_team).getTeam();

                if( current_team.equals(old_team) ){
                    String user_fullname = game.getGame().get(t_game).getTeam().get(t_team).getFull_name();
                    String user_position = game.getGame().get(t_game).getTeam().get(t_team).getPosition();
                    String user_username = game.getGame().get(t_game).getTeam().get(t_team).getUsername();
                    listView_Array.add( user_fullname + " , " + user_username + " : " + user_position );    ///Presented, searchd by...
                    listView_Index.add( user_username );
                }else{
                    listView_Array.add( "Time : " + current_team + " --------------" );
                    listView_Index.add( "N/A" );
                    String user_fullname = game.getGame().get(t_game).getTeam().get(t_team).getFull_name();
                    String user_position = game.getGame().get(t_game).getTeam().get(t_team).getPosition();
                    String user_username = game.getGame().get(t_game).getTeam().get(t_team).getUsername();
                    listView_Array.add( user_fullname + " , " + user_username + " : " + user_position );    ///Presented, searchd by...
                    listView_Index.add( user_username );
                }

                old_team = current_team;

            }
        }

        if( listView_Array != null ){
            listView = (ListView) findViewById(R.id.list_View);
            ArrayAdapter arrayAdapter = new ArrayAdapter( this , android.R.layout.simple_list_item_1 , listView_Array );
            listView.setAdapter( arrayAdapter );
            listView.setOnItemClickListener( listClick );
        }

    }


    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String sel_username = listView_Index.get( position );

            if( !listView_Index.get( position ).contains("N/A") ){
                goto_seeTeams( sel_username );
            }

        }
    };

    void goto_seeTeams( String sel_username ){

        Intent seeTeams = new Intent( this , See_Teams.class );

        for( int t_game = 0 ; t_game < game.getGame().size() ; t_game++ ) {                              ///For each team
            for (int t_team = 0; t_team < game.getGame().get(t_game).getTeam().size(); t_team++) {    ///for each user

                String user_fullname = game.getGame().get(t_game).getTeam().get(t_team).getFull_name();
                String user_username = game.getGame().get(t_game).getTeam().get(t_team).getUsername();
                String user_position = game.getGame().get(t_game).getTeam().get(t_team).getPosition();
                String user_team     = game.getGame().get(t_game).getTeam().get(t_team).getTeam();
                String user_photo    = game.getGame().get(t_game).getTeam().get(t_team).getPhoto();

                if( sel_username.equals( user_username ) ){
                    seeTeams.putExtra( "full_name" , user_fullname );
                    seeTeams.putExtra( "username" , user_username );
                    seeTeams.putExtra( "position" , user_position );
                    seeTeams.putExtra( "team" , user_team );
                    seeTeams.putExtra( "photo" , user_photo );

                    Log.d("full_name", "goto_seeTeams: " + user_fullname);
                    Log.d("username", "goto_seeTeams: " + user_username);
                    Log.d("position", "goto_seeTeams: " + user_position);
                    Log.d("team", "goto_seeTeams: " + user_team);
                    Log.d("photo", "goto_seeTeams: " + user_photo);

                    startActivity(seeTeams);
                }

            }
        }


    }

    public void srch_image(View view) {

        Intent intent = new Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedURI = data.getData(); //The uri with the location of the file

        String fullFilePath = UriUtils.getPathFromUri(this, selectedURI);
        photo_path = fullFilePath;
        File selectedFile = new File(fullFilePath);

        String filetype = MimeTypeMap.getFileExtensionFromUrl(fullFilePath);

        Log.d("Sel file: ", "onActivityResult: " + fullFilePath);
        Log.d("File type: ", "onActivityResult: " + filetype);

    }

}