package com.aros.naufal.qrreader.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.aros.naufal.qrreader.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

//package com.solfamidas.yamba.qrcardpocreader;

public class ShowQRActivity extends AppCompatActivity {

    private TextView txtSend;
    ImageView qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qr);
        //qrCode=(ImageView)view.findViewById(R.id.imageView);
        //Localizar los controles
        txtSend = (TextView)findViewById(R.id.TxtRead); //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String textToQr = bundle.getString("TEXT");
        //Construimos el mensaje a mostrar
        //txtSend.setText("Hola " + textToQr);

        qrCode = (ImageView)findViewById(R.id.imageQRcode);
        int ancho=getWindowManager().getDefaultDisplay().getWidth();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textToQr, BarcodeFormat.QR_CODE,ancho,ancho);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}