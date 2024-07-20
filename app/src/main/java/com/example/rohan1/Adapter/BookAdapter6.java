package com.example.rohan1.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rohan1.BookItem;
import com.example.rohan1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.List;
import java.util.Map;

public class BookAdapter6 extends RecyclerView.Adapter<BookAdapter6.ViewHolder> {

    private Context context;
    private List<BookItem> bookItemList;
    private Map<String, String> pdfMap;


    public BookAdapter6(Context context, List<BookItem> bookItemList, Map<String, String> pdfMap) {
        this.context = context;
        this.bookItemList = bookItemList;
        this.pdfMap = pdfMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookItem bookItem = bookItemList.get(position);
        holder.bookName.setText(bookItem.getName());
        holder.bookImage.setImageResource(bookItem.getImageResource());

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    BookItem clickedItem = bookItemList.get(adapterPosition);
                    String subject = clickedItem.getName();
                    String pdfName = pdfMap.get(subject);
                    if (pdfName != null) {
                        retrieveAndOpenPdf(pdfName);
                    } else {
                        Toast.makeText(context, "PDF not found for subject: " + subject, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
        }
    }

    private void retrieveAndOpenPdf(String pdfName) {
        StorageReference pdfRef = FirebaseStorage.getInstance().getReference().child(pdfName);
        pdfRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                openPdf(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed to retrieve PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openPdf(Uri pdfUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
