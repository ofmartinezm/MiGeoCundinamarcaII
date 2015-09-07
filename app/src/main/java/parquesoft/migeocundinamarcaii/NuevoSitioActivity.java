package parquesoft.migeocundinamarcaii;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NuevoSitioActivity extends ActionBarActivity {
    private EditText etLatitud;
    private EditText etLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sitio);

        etLatitud=(EditText)findViewById(R.id.etLat);
        etLongitud=(EditText)findViewById(R.id.etLon);
        Bundle bundle = getIntent().getExtras();
        etLatitud.setText(bundle.getString("latitud"));
        etLongitud.setText(bundle.getString("longitud"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_sitio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void salir(View v){

        finish();
    }
}
