package co.edu.konradlorenz.cardview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeriesAdapter adapter;
    private List<Serie> serieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        serieList = new ArrayList<>();
        adapter = new SeriesAdapter(this, serieList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.fondo).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.hill,
                R.drawable.distrito,
                R.drawable.kissme,
                R.drawable.rain,
                R.drawable.dark,
                R.drawable.insatiable,
                R.drawable.elite,
                R.drawable.oa,
                R.drawable.innocents,
                R.drawable.gossip
        };



        Serie series = new Serie("La Maldicion de Hill House", 1, covers[0],"The Haunting of Hill House es una serie de televisión de terror sobrenatural estadounidense creada por Mike Flanagan, y se basa en la novela homónima de 1959 de Shirley Jackson. La serie se estrenó el 12 de octubre de 2018 y está compuesta por diez episodios.");
        serieList.add(series);

        series = new Serie("Distrito Salvaje", 1, covers[1],"Un guerrillero escapa de la jungla tras la firma del Tratado de Paz colombiano, tratando de escapar de su pasado para reinsertarse en la sociedad.");
        serieList.add(series);

        series = new Serie("Kiss Me First", 1, covers[2],"Kiss Me First es una serie de televisión británica de drama creada por Bryan Elsley para Netflix y Channel 4. La serie comenzó a emitirse el 2 de abril de 2018 en Channel 4.");
        serieList.add(series);
        series = new Serie("The Rain", 1, covers[3],"The Rain es una serie de televisión web danesa creada por Jannik Tai Mosholt, Esben Toft Jacobsen y Christian Potalivo. Se estrenó en Netflix el 4 de mayo de 2018. El 30 de mayo de 2018, se anunció la renovación para una segunda temporada");
        serieList.add(series);

        series = new Serie("Dark", 1, covers[4],"La desaparición de dos niños muestra los vínculos entre cuatro familias y expone el pasado de una pequeña ciudad.");
        serieList.add(series);

        series = new Serie("Insatiable", 1, covers[5],"Insatiable es una serie de televisión estadounidense de comedia negra de Netflix creada por Lauren Gussis. La serie está protagonizada por Debby Ryan y se estrenó el 10 de agosto de 2018.");
        serieList.add(series);

        series = new Serie("Elite", 1, covers[6],"Las Encinas es el colegio más exclusivo de España, el lugar donde estudian los hijos de la élite y donde acaban de ser admitidos tres jóvenes de clase baja, procedentes de un colegio público en ruinas.");
        serieList.add(series);

        series = new Serie("The OA", 1, covers[7],"Prairie Johnson es una mujer ciega que misteriosamente desaparece. Siete años más tarde resurge, y su vista se ha restaurado.");
        serieList.add(series);

        series = new Serie("The Innocents", 1, covers[8],"The Innocents es una serie de televisión sobrenatural británica creada por Hania Elkington y Simon Duric. La serie se estrenó el 24 de agosto de 2018 en Netflix.");
        serieList.add(series);

        series = new Serie("Gossip Girl", 1, covers[9],"Gossip Girl comparte en internet exclusiva información sobre escándalos, creando gran polémica respecto a lo que sucede en la alta sociedad neoyorquina, pero siempre manteniendo su identidad secreta.");
        serieList.add(series);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
