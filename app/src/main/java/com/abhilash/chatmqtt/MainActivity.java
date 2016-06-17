package com.abhilash.chatmqtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdpater chatAdpater;
    List<ChatMessage> messages;
    EditText messageEditText;
    Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messages = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.chatList);
        messageEditText = (EditText) findViewById(R.id.messageText);
        sendButton = (Button) findViewById(R.id.messageSendButton);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        //layoutManager.setStackFromEnd(true);
        //layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        chatAdpater = new ChatAdpater(messages);
        recyclerView.setAdapter(chatAdpater);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageEditText.length() > 0)
                {
                    ChatMessage chatMessage = new ChatMessage("Abhilash",messageEditText.getText().toString());
                    messages.add(chatMessage);
                    chatAdpater.notifyItemInserted(messages.size()-1);
                    recyclerView.scrollToPosition(messages.size()-1);
                    messageEditText.setText("");
                }
            }
        });

        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(messageEditText.length() > 0)
                {
                    ChatMessage chatMessage = new ChatMessage("Shashank",messageEditText.getText().toString());
                    messages.add(chatMessage);
                    chatAdpater.notifyItemInserted(messages.size()-1);
                    recyclerView.scrollToPosition(messages.size()-1);
                    messageEditText.setText("");
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
