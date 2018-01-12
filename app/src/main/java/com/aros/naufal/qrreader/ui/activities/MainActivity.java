package com.aros.naufal.qrreader.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aros.naufal.qrreader.R;
import com.aros.naufal.qrreader.data.database.entities.Card;
import com.aros.naufal.qrreader.ui.adapters.CardListAdapter;
import com.aros.naufal.qrreader.viewmodel.CardViewModel;
import com.google.zxing.Result;

import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private CardViewModel mCardViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);

        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);

        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getTitle().equals("Capture QR")) {
                    Log.d(TAG, "Capturing QR");
                    setContentView(mScannerView);
                    mScannerView.startCamera();
                }
                if (menuItem.getTitle().equals("Profile")) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                }
                if (menuItem.getTitle().equals("Show my QR")) {
                    clickBtnGenerateQR(findViewById(android.R.id.content));
                    return true;
                }
                Toast.makeText(MainActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final CardListAdapter adapter = new CardListAdapter(this);
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mCardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);

        mCardViewModel.getAllCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                adapter.setCards(cards);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result result) {
        // Do something with the result here
        System.out.println("RESULT ==>> " + result.getText());
        Log.d("handler", result.getText()); // Prints scan results
        Log.d("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setNeutralButton("Añadir contacto", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCardViewModel.instert(new Card(result.getText()));
                Toast.makeText(MainActivity.this, "Confirmado", Toast.LENGTH_LONG).show();
            }
        });

        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();

        alert1.show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

    //Generating QR
    public void clickBtnGenerateQR(View v) {
        //Intent creation
        Intent intent = new Intent(MainActivity.this, ShowQRActivity.class);

        //Pass info through activities
        Bundle b = new Bundle();
        b.putString("TEXT","Miguel");
        //Add info
        String cadena = "Miguel";
        intent.putExtras(b);
        //Start the new activity
        startActivity(intent);
    }
}
