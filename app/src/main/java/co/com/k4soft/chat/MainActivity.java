package co.com.k4soft.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ListView listViewChat;
    private EditText txtMensaje;
    final List<String> mensajes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewChat = findViewById(R.id.listViewChat);
        txtMensaje = findViewById(R.id.txtMensaje);
        loadInfo();
    }

    private void loadInfo() {
        DatabaseReference  databaseReference = getDatabaseReference("CHAT");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mensajes.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    mensajes.add(snapshot.child("mensaje").getValue().toString());
                }
                String[] array = new String[mensajes.size()];
                for(int i = 0; i <mensajes.size(); i++){
                    array[i] = mensajes.get(i);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
                listViewChat.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private DatabaseReference getDatabaseReference(String path) {
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
        return FirebaseDatabase.getInstance(firebaseApp).getReference(path);
    }


    public void enviar(View view) {
        if(!"".equals(txtMensaje.getText().toString())){
            UUID id = UUID.randomUUID();
            DatabaseReference chatItem = getDatabaseReference("CHAT");
            DatabaseReference messageItem = chatItem.child(id.toString());
            messageItem.child("mensaje").setValue(txtMensaje.getText().toString());
        }
        txtMensaje.setText("");
    }
}
