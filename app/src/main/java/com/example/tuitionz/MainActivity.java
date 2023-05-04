package com.example.tuitionz;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider =findViewById(R.id.image_slider);
        final List<SlideModel> remoteimge=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Tuition").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    remoteimge.add(new SlideModel((dataSnapshot.child("title").getValue())
                            .toString(),(dataSnapshot.child("url").getValue()).toString(), ScaleTypes.FIT));
                imageSlider.setImageList(remoteimge,ScaleTypes.FIT);
                imageSlider.setItemChangeListener(new ItemChangeListener() {
                    @Override
                    public void onItemChanged(int i) {
                       if(i==0){
                           Toast.makeText(MainActivity.this, "Clicked One", Toast.LENGTH_SHORT).show();
                       } else if (i==1) {
                           Toast.makeText(MainActivity.this, "Clicked Second", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(MainActivity.this, "Clicked Third", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}