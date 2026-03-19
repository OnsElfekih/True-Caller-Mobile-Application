package ELFEKIHOns.truecaller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyContactRecylerAdapter extends RecyclerView.Adapter<MyContactRecylerAdapter.MyViewHolder> {
    Context context;
    ArrayList<Contact> contacts;

    public MyContactRecylerAdapter(Context con, ArrayList<Contact> contacts) {
        this.context = con;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyContactRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(R.layout.view_contact, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactRecylerAdapter.MyViewHolder holder, int position) {
        Contact c = contacts.get(position);
        holder.tfirst.setText(c.getFirst());
        holder.tphone.setText(c.getPhone());
        holder.tlast.setText(c.getLast());

        if (c.getIsFav() == 1) {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }

        String phone = c.getPhone();
        int bgColor = Color.WHITE;
        int phoneColor = Color.parseColor("#2196F3");

        if (phone != null && phone.length() >= 1) {
            if (phone.startsWith("5")) {
                bgColor = Color.parseColor("#FFF3E0");
                phoneColor = Color.parseColor("#FF9800");
            } else if (phone.startsWith("2")) {
                bgColor = Color.parseColor("#FFEBEE");
                phoneColor = Color.parseColor("#F44336");
            } else if (phone.startsWith("9") || phone.startsWith("4")) {
                bgColor = Color.parseColor("#E3F2FD");
                phoneColor = Color.parseColor("#1976D2");
            }
        }
        holder.card.setCardBackgroundColor(bgColor);
        holder.tphone.setTextColor(phoneColor);
    }

    @Override
    public int getItemCount() {
        return (contacts != null) ? contacts.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tlast, tfirst, tphone;
        ImageView edit, delete, call, sms, imgFav, location;
        CardView card;

        public MyViewHolder(@NonNull View v) {
            super(v);
            tlast = v.findViewById(R.id.tvnom_contact);
            tfirst = v.findViewById(R.id.tvpseudo_contact);
            tphone = v.findViewById(R.id.tvnumero_contact);
            card = v.findViewById(R.id.card_contact);
            imgFav = v.findViewById(R.id.imgFav_contact);
            location = v.findViewById(R.id.imageViewLocation_contact);

            edit = v.findViewById(R.id.imageViewEdit_contact);
            delete = v.findViewById(R.id.imageViewDelete_contact);
            call = v.findViewById(R.id.imageViewCall_contact);
            sms = v.findViewById(R.id.imageViewSms_contact);

            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact targetContact = contacts.get(pos);
                        envoiPosition(targetContact);
                    }
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact c = contacts.get(pos);
                        int nouvelEtat = (c.getIsFav() == 1) ? 0 : 1;
                        ContactManager manager = new ContactManager(context);
                        manager.Ouvrir();
                        manager.changerFavori(c.getId(), nouvelEtat);
                        manager.fermer();
                        c.setIsFav(nouvelEtat);
                        notifyItemChanged(pos);
                    }
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Modification");
                    View vd = LayoutInflater.from(context).inflate(R.layout.activity_edit, null);
                    builder.setView(vd);
                    EditText edNom = vd.findViewById(R.id.edNom_edit);
                    EditText edPrenom = vd.findViewById(R.id.edPrenom_edit);
                    EditText edNum = vd.findViewById(R.id.edNumber_edit);
                    Button btnSave = vd.findViewById(R.id.btnSave_edit);
                    int pos = getAbsoluteAdapterPosition();
                    if(pos == RecyclerView.NO_POSITION) return;
                    Contact c = contacts.get(pos);
                    edNom.setText(c.getLast());
                    edPrenom.setText(c.getFirst());
                    edNum.setText(c.getPhone());
                    AlertDialog dialog = builder.create();
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String n = edNom.getText().toString();
                            String p = edPrenom.getText().toString();
                            String tel = edNum.getText().toString();
                            if (!n.isEmpty() && !p.isEmpty() && !tel.isEmpty()) {
                                ContactManager manager = new ContactManager(context);
                                manager.Ouvrir();
                                manager.modifier(c.getId(), p, n, tel);
                                manager.fermer();
                                c.setFirst(p);
                                c.setLast(n);
                                c.setPhone(tel);
                                notifyItemChanged(pos);
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if(pos == RecyclerView.NO_POSITION) return;
                    Contact c = contacts.get(pos);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Confirmation").setMessage("Supprimer ?").setPositiveButton("Oui", (dialog, i) -> {
                        ContactManager manager = new ContactManager(context);
                        manager.Ouvrir();
                        manager.supprimer(c.getId());
                        manager.fermer();
                        contacts.remove(pos);
                        notifyItemRemoved(pos);
                    }).setNegativeButton("Non", null).show();
                }
            });

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact c = contacts.get(pos);
                        Intent intent = new Intent(context, EnvoiSms.class);
                        intent.putExtra("NOM", c.getFirst() + " " + c.getLast());
                        intent.putExtra("NUMERO", c.getPhone());
                        context.startActivity(intent);
                    }
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact c = contacts.get(pos);
                        Intent intent = new Intent(context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ? Intent.ACTION_CALL : Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + c.getPhone()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    private void envoiPosition(Contact c) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED 
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            
            Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (loc == null) {
                loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            if (loc != null) {
                String lat = String.valueOf(loc.getLatitude());
                String lon = String.valueOf(loc.getLongitude());

                new ActionAjout(c.getPhone(), lon, lat).execute();

                String message = "Ma position est : Latitude=" + lat + " , Longitude=" + lon;
                SmsManager.getDefault().sendTextMessage(c.getPhone(), null, message, null, null);
                
                Toast.makeText(context, "Coordonnées partagées avec " + c.getFirst(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Position non disponible. Activez le GPS.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Permissions manquantes", Toast.LENGTH_SHORT).show();
        }
    }

    class ActionAjout extends AsyncTask {
        String num, lon, lat, pseudo;

        ActionAjout(String num, String lon, String lat) {
            this.num = num;
            this.lon = lon;
            this.lat = lat;
            SharedPreferences sp = context.getSharedPreferences("session_truecaller", Context.MODE_PRIVATE);
            this.pseudo = sp.getString("username", "User_TrueCaller");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            JSONParser parser = new JSONParser();
            HashMap<String, String> params = new HashMap<>();
            params.put("numero", num);
            params.put("pseudo", pseudo);
            params.put("longitude", lon);
            params.put("latitude", lat);
            return parser.makeHttpRequest(DataBaseConfig.URL_AddPosition, "POST", params);
        }

        @Override
        protected void onPostExecute(Object o) {
            JSONObject response = (JSONObject) o;
            try {
                if (response != null && response.getInt("success") == 1) {
                    android.util.Log.d("SERVER", "Position enregistrée");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
