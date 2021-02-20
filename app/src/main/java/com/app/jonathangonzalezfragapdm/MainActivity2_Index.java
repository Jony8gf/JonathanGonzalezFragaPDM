package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2_Index extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private BottomNavigationView navigationView;
    private Button bt_report;

    List<ItemModel> items = new ArrayList<>();

    Usuario usuarioAux;

    DatabaseReference bbdd;

    ArrayList<Usuario> listado = new ArrayList<Usuario>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__index);

        //Asignacion de BotonNavigation
        navigationView = findViewById(R.id.menuBotonNavegacion_Index);

        //Asignacion de Botton
        bt_report = findViewById(R.id.button_report);

        bbdd = FirebaseDatabase.getInstance().getReference();

        bbdd.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    Usuario user = datasnapshot.getValue(Usuario.class);

                    assert user != null;
                    String nombre = user.getNombre();
                    Usuario userAux = user;

                    listado.add(userAux);
                    items.add(new ItemModel(R.drawable.perfilxdefecto, userAux.getNombre() , "24", userAux.getGenero()));

                    //Toast.makeText(MainActivity2_Index.this, nombre, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //ArrayAdapter<String> adaptador;
                //ArrayList<String> listado = new ArrayList<String>();

                for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    Usuario user = datasnapshot.getValue(Usuario.class);

                    assert user != null;
                    String nombre = user.getNombre();
                    listado.add(nombre);
                    Toast.makeText(MainActivity2_Index.this, "Holas", Toast.LENGTH_LONG).show();

                }

                //adaptador = new ArrayAdapter<String>(MainActivity2_Index.this, R.layout.item_card, listado);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() ==  R.id.menu_home){

                }

                if(item.getItemId() ==  R.id.menu_msg){
                    Toast.makeText(MainActivity2_Index.this, "Has pulsado Mensajes", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId() ==  R.id.menu_notificaciones){
                    Toast.makeText(MainActivity2_Index.this, "Has pulsado Notificaciones", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId() ==  R.id.menu_perfil){
                    Intent intent = new Intent(MainActivity2_Index.this, MainActivity2_Perfil.class);
                    startActivity(intent);
                    finish();
                }


                return true;
            }
        });

        /*
        bt_report.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity2_Index.this, v);
                popup.setOnMenuItemClickListener(MainActivity2_Index.this);
                popup.inflate(R.menu.menu_report);
                popup.show();
                return false;
            }
        });
         */

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(MainActivity2_Index.this, "Direction Right", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top){
                    Toast.makeText(MainActivity2_Index.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(MainActivity2_Index.this, "Direction Left", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(MainActivity2_Index.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nombre: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nombre: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }



    private void paginate() {
        List<ItemModel> viejo = adapter.getItems();
        List<ItemModel> nuevo = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(viejo, nuevo);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(nuevo);
        hasil.dispatchUpdatesTo(adapter);
    }


    private List<ItemModel> addList() {

        /*
        for (int i = 0; i < listado.size(); i++){
            usuarioAux.setNombre(listado.get(i).getNombre());
            usuarioAux.setGenero(listado.get(i).getGenero());
            items.add(new ItemModel(R.drawable.fotoperfilprueba, usuarioAux.getNombre() , "24", usuarioAux.getGenero()));
        }

         */

        //items.add(new ItemModel(R.drawable.fotoperfilprueba, "TT" , "24", "Mujer"));
        //items.add(new ItemModel(R.drawable.fotoperfilprueba, "Sara", "24", "Jimenez"));
        //items.add(new ItemModel(R.drawable.fotoperfilprueba, , "24", "Jimenez"));
        //items.add(new ItemModel(R.drawable.fotoperfil2, "Berta", "20", "Malansa"));
        //items.add(new ItemModel(R.drawable.fotoperfil3, "Susana", "27", "Jonguez"));
        //items.add(new ItemModel(R.drawable.fotoperfil4, "Martinar", "19", "Balando"));
        //items.add(new ItemModel(R.drawable.fotoperfil5, "Elena", "25", "Hurtado"));

        //items.add(new ItemModel(R.drawable.fotoperfil6, "Jose Antonio", "24", "Fernandez"));
        //items.add(new ItemModel(R.drawable.fotoperfil7, "John", "20", "Abascal"));
        //items.add(new ItemModel(R.drawable.fotoperfil8, "Sergio", "27", "Garcia"));
        //items.add(new ItemModel(R.drawable.fotoperfil9, "Manolo", "19", "De la Hoz"));
        //items.add(new ItemModel(R.drawable.fotoperfil10, "Kike", "25", "Moro"));

        return items;
    }

    public void mostrarMenuPopUp(View view){
        PopupMenu popup = new PopupMenu(MainActivity2_Index.this, view);
        popup.setOnMenuItemClickListener(MainActivity2_Index.this);
        popup.inflate(R.menu.menu_report);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reportar_usuario:
                enviarReport();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public  void enviarReport(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","jonathan8gf@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "LIEBE APP - REPORT USER ");
        startActivity(Intent.createChooser(emailIntent, "Report User"));
    }


}