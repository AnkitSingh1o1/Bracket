package com.example.bracket;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    private Cryptography cryptography;
    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            cryptography = new Cryptography();
            try {
                messageTextView.setText(cryptography.AESDecryptionMethod(message.getText()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        authorTextView.setText(message.getName());

        return convertView;
    }

}
