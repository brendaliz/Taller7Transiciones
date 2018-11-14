package co.edu.konradlorenz.cardview;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.Explode;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.transition.TransitionSet;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by jiacontrerasp on 02/04/18.
 */
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {



    private Context mContext;
    private List<Serie> serieList;
    private  Context contextIntent;
    private ImageView imageView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, temporada;

        public ImageView thumbnail;
        private View elementView;

        public MyViewHolder(View view) {

            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            temporada = (TextView) view.findViewById(R.id.temporada);
            elementView = view;
        }
    }


    public SeriesAdapter(Context mContext, List<Serie> serieList) {
        this.mContext = mContext;
        this.serieList = serieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Serie serie = serieList.get(position);

        holder.elementView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                TransitionSet transitionSet = new TransitionSet();
                Slide slide = new Slide(Gravity.START);
                Fade fadeOut = new Fade(Fade.OUT);

                transitionSet.addTransition(slide);
                transitionSet.addTransition(fadeOut);

                transitionSet.setInterpolator(new DecelerateInterpolator());


                Intent i = new Intent(mContext,SerieDetailActivity.class);
                i.putExtra("thumbnail", serie.getThumbnail());
                i.putExtra("temporadas",serie.getTemporadas());

                i.putExtra("nombre", serie.getName());
                i.putExtra("description", serie.getDescription());

                mContext.startActivity(i);


                // REVISAR LA PARTE DEL STRING
                Toast.makeText(mContext, serie.getName(),Toast.LENGTH_SHORT).show();

            }
        });
        holder.title.setText(serie.getName());
        holder.temporada.setText(serie.getTemporadas() + " Temporadas");

        // loading serie cover using Glide library
        Glide.with(mContext).load(serie.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return serieList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view){
        int centerX = 0;
        int centerY = 0;
        int starRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX,centerY,starRadius,endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }
}
