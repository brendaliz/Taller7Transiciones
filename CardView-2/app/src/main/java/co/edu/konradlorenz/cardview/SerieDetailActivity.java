package co.edu.konradlorenz.cardview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SerieDetailActivity extends AppCompatActivity {

    private ImageView imagenAlbum;
    private ListView listaTemp;
    private ListView listaCap;
    private TextView descripcion,capitulos;
    private TextView temporadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);





        Bundle extras = getIntent().getExtras();

        int thumbnail = extras.getInt("thumbnail");
        imagenAlbum = (ImageView) findViewById(R.id.imagenSerie);
        imagenAlbum.setImageResource(thumbnail);

        int temporada = extras.getInt("temporadas");
        temporadas = (TextView) findViewById(R.id.txtTemporadas);
        temporadas.setText("Temporadas: "+temporada);

        capitulos = (TextView) findViewById(R.id.txtCapitulos);
        capitulos.setText("Capitulos");



        listaTemp = (ListView) findViewById(R.id.listaTemporadas);
        ArrayList listaCantidadTemporadas = new ArrayList();
        for (int i = 1; i<=temporada; i++){
            listaCantidadTemporadas.add("Temporada "+ i);
        }
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaCantidadTemporadas);

        listaTemp.setAdapter(adaptador);

       listaTemp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"Seleciona: " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

                listaCap = (ListView) findViewById(R.id.listaCapitulos);
                ArrayList listaCantidadCapitulos = new ArrayList();
                for (int i = 0; i<10; i++){
                    listaCantidadCapitulos.add("Capitulo: "+ i);
                }
                ArrayAdapter adaptador1 = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listaCantidadCapitulos);

                listaCap.setAdapter(adaptador1);

                listaCap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(parent.getContext(),"Seleciona: " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                        Bundle extras2 = getIntent().getExtras();

                        String description = extras2.getString("description");
                        descripcion = (TextView) findViewById(R.id.descripcionCapitulo);
                        descripcion.setText("Descripcion Capitulo: "+" \n"+ description);

                    }
                });

            }
        });



    }

}
