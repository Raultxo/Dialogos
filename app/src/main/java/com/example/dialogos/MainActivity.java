package com.example.dialogos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogoCerrar.InterfazDialogoCerrar, DialogoInicio.InterfazDialogoInicioSesion {

    private ViewPager viewPager;
    private ListView listaFearless, listaRed, lista1989;
    private Cancion[] cancionesFearless, cancionesRed, canciones1989;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoInicio dialogoInicio = new DialogoInicio();
        dialogoInicio.show(fragmentManager, "DilogoInicio");

        cargarDatos();

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageAdapter());
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void cargarDatos() {
        cancionesFearless = new Cancion[] {
                new Cancion("Fearless (Taylor's Version)", "4:01", "https://open.spotify.com/track/77sMIMlNaSURUAXq5coCxE?si=7cd5debddac64203"),
                new Cancion("Fifteen (Taylor's Version)", "4:54", "https://open.spotify.com/track/2nqio0SfWg6gh2eCtfuMa5?si=cfeac9bf22184fdc"),
                new Cancion("Love Story (Taylor's Version)", "3:55", "https://open.spotify.com/track/6YvqWjhGD8mB5QXcbcUKtx?si=b023dd1c5a9443db"),
                new Cancion("Hey Stephen (Taylor's Version)", "4:14", "https://open.spotify.com/track/550erGcdD9n6PnwxrvYqZT?si=33559389ca03427a"),
                new Cancion("White Horse (Taylor's Version)", "3:54", "https://open.spotify.com/track/5YL553x8sHderRBDlm3NM3?si=e4485f32bba54b29"),
                new Cancion("You Belong With Me (Taylor's Version)", "3:51", "https://open.spotify.com/track/1qrpoAMXodY6895hGKoUpA?si=852d9e4907954319"),
                new Cancion("Breathe (feat. Colbie Caillat) (Taylor's Version)", "4:23", "https://open.spotify.com/track/7HC7R2D8WjXVcUHJyEGjRs?si=623b805702df437b")
        };
        cancionesRed = new Cancion[] {
                new Cancion("State of Grace (Taylor's Version)", "4:55", "https://open.spotify.com/track/6lzc0Al0zfZOIFsFvBS1ki?si=a727d09536e34844"),
                new Cancion("All Too Well (10 Minute Version) (Taylor's Version) (From The Vault)", "10:13", "https://open.spotify.com/track/5enxwA8aAbwZbf5qCHORXi?si=5b9bf10388f94e58")
        };
        canciones1989 = new Cancion[] {
                new Cancion("Wildest Dreams (Taylor's Version)", "3:40", "https://open.spotify.com/track/1Ov37jtRQ2YNAe8HzfczkL?si=144b11e919264858")
        };
    }

    public void albumFearless(View v) {
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/album/4hDok0OAJd57SGIT8xuWJH?si=EPrbiyOLSVmrllardeJIAg"));
        startActivity(intent);
        finish();
    }

    public void albumRed(View v) {
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/album/6kZ42qRrzov54LcAk4onW9?si=24hK0KdEQHq4O1gr0ZODWg"));
        startActivity(intent);
        finish();
    }

    public void album1989(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("¡ATENCIÓN!");
        alertDialog.setMessage("Este album todavía no está dispoible");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {}});
        alertDialog.show();
    }

    class PageAdapter extends PagerAdapter {
        private LinearLayout pestana1, pestana2, pestana3;
        private final int[] pestanas ={R.string.tab1, R.string.tab2, R.string.tab3};
        private NotificationManager notificationManager;
        static final String CANAL_ID = "canal1";
        static final int NOTIFICAION_ID = 1;

        @Override
        public int getCount() {
            return 3;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String s = getString(pestanas[position]);
            return s;
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View page;
            switch (position) {
                case 0:
                    if (pestana1 == null) {
                        pestana1 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fearless, container, false);
                    }
                    page = pestana1;
                    listaFearless = (ListView) pestana1.findViewById(R.id.listaFearless);
                    AdaptadorCancion adaptadorContacto = new AdaptadorCancion(pestana1.getContext(), cancionesFearless);
                    listaFearless.setAdapter(adaptadorContacto);
                    listaFearless.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cancion opcionSeleccionada = (Cancion) adapterView.getItemAtPosition(i);
                            enviarNotificacion();
                            Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(opcionSeleccionada.getLinkSpotify()));
                            startActivity(intent);
                            finish();
                        }
                    });
                    break;
                case 1:
                    if (pestana2 == null) {
                        pestana2 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.red,container,false);
                    }
                    page=pestana2;
                    listaRed = (ListView) pestana2.findViewById(R.id.listaRed);
                    AdaptadorCancion adaptadorEstados = new AdaptadorCancion(pestana2.getContext(), cancionesRed);
                    listaRed.setAdapter(adaptadorEstados);
                    listaRed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cancion opcionSeleccionada = (Cancion) adapterView.getItemAtPosition(i);
                            enviarNotificacion();
                            Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(opcionSeleccionada.getLinkSpotify()));
                            startActivity(intent);
                            finish();
                        }
                    });
                    break;
                default:
                    if (pestana3 == null) {
                        pestana3 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.ts1989,container,false);
                    }
                    page=pestana3;
                    lista1989 = (ListView) pestana3.findViewById(R.id.lista1989);
                    AdaptadorCancion adaptadorLlamada = new AdaptadorCancion(pestana3.getContext(), canciones1989);
                    lista1989.setAdapter(adaptadorLlamada);
                    lista1989.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cancion opcionSeleccionada = (Cancion) adapterView.getItemAtPosition(i);
                            enviarNotificacion();
                            Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(opcionSeleccionada.getLinkSpotify()));
                            startActivity(intent);
                            finish();
                        }
                    });
                    break;
            }
            container.addView(page, 0);
            return page;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        private void enviarNotificacion() {
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID, "Mis notificaiones", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Notificacion");
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder notificaion = new NotificationCompat.Builder(MainActivity.this, CANAL_ID)
                    .setSmallIcon(R.drawable.lupa_foreground)
                    .setContentTitle("\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25")
                    .setContentText("Tienes buen gusto");

            notificationManager.notify(NOTIFICAION_ID, notificaion.build());
        }
    }

    @Override
    public void onPossitiveButtonClick() {
        finish();
    }

    @Override
    public void onPossitiveButtonClick2(String nombre, String pass) {
       if(!nombre.equals("usuario1") || !pass.equals("123456")) {
           FragmentManager fragmentManager = getSupportFragmentManager();
           DialogoInicio dialogoInicio = new DialogoInicio();
           dialogoInicio.show(fragmentManager, "DialogoInicio");
        }
    }

    @Override
    public void onNegativeButtonClick2() {
        finish();
    }

    public void salir(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoCerrar dialogoCerrar = new DialogoCerrar();
        dialogoCerrar.show(fragmentManager, "DialogoSalir");
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoCerrar dialogoCerrar = new DialogoCerrar();
        dialogoCerrar.show(fragmentManager, "DialogoSalir");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("¡ATENCIÓN!");
        alertDialog.setMessage("Opción no implementada");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {}});
        switch (item.getItemId()) {
            case R.id.lupa:
                alertDialog.show();
                return true;
            case R.id.singles:
                alertDialog.show();
                return true;
            case R.id.ajustes:
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}