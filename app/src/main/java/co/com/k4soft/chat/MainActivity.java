package co.com.k4soft.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

    }

    public void enviar(View view) {
    }
}
