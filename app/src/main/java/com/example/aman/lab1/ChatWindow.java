package com.example.aman.lab1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    ListView chatList = null;
    Button sendChatButton;
    ArrayList<String> chatArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        final EditText chatText = (EditText) findViewById(R.id.chatText);
        chatList = (ListView) findViewById(R.id.chatList);
        sendChatButton = (Button) findViewById(R.id.sendButton);

        //in this case, “this” is the ChatWindow, which is-A Context objectChatAdapter
        final ChatAdapter messageAdapter = new ChatAdapter(this,5);
        chatList.setAdapter(messageAdapter);//listview

        sendChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatArray.add(chatText.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                chatText.setText("");
            }
        });

    }

    class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context context, int i){
           super(context, 0);
        }

        public int getCount() {
            return chatArray.size();
        }

        public String getItem(int position){
            return chatArray.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;

        }
    }
}
