package ELFEKIHOns.truecaller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyContactAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> contacts;

    MyContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inf = LayoutInflater.from(context);
        View l = inf.inflate(R.layout.view_contact, null);

        TextView tlast = l.findViewById(R.id.tvnom_contact);
        TextView tfirst = l.findViewById(R.id.tvpseudo_contact);
        TextView tphone = l.findViewById(R.id.tvnumero_contact);

        ImageView edit = l.findViewById(R.id.imageViewEdit_contact);
        ImageView delete = l.findViewById(R.id.imageViewDelete_contact);
        ImageView call = l.findViewById(R.id.imageViewCall_contact);

        Contact c = contacts.get(position);
        tfirst.setText(c.getFirst());
        tphone.setText(c.getPhone());
        tlast.setText(c.getLast());

        // Action sur le bouton Edit
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(context, Edition.class);
                i.putExtra("ID", c.getId());
                i.putExtra("FIRST", c.getFirst());
                i.putExtra("LAST", c.getLast());
                i.putExtra("PHONE", c.getPhone());
                context.startActivity(i);*/
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Modification");

                // 1. Gonfler le layout
                View vd = LayoutInflater.from(context).inflate(R.layout.activity_edit, null);
                builder.setView(vd);

                // 2. RÉCUPÉRATION des composants depuis le layout 'vd'
                EditText edNom = vd.findViewById(R.id.edNom_edit);
                EditText edPrenom = vd.findViewById(R.id.edPrenom_edit);
                EditText edNum = vd.findViewById(R.id.edNumber_edit);
                Button btnSave = vd.findViewById(R.id.btnSave_edit);
                Button btnBack = vd.findViewById(R.id.btnBack_edit);
                Button btnInit = vd.findViewById(R.id.btnInit_edit);

                // 3. AFFICHAGE des données (récupérées de l'objet contact 'c')
                edNom.setText(c.getLast());
                edPrenom.setText(c.getFirst());
                edNum.setText(c.getPhone());

                AlertDialog dialog = builder.create();

                // 4. Logique du bouton Update (Sauvegarde dans la BDD)
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n = edNom.getText().toString();
                        String p = edPrenom.getText().toString();
                        String tel = edNum.getText().toString();

                        if (!n.isEmpty() && !p.isEmpty() && !tel.isEmpty()) {
                            // Appel au Manager pour mettre à jour la BDD
                            ContactManager manager = new ContactManager(context);
                            manager.Ouvrir();
                            int res = manager.modifier(c.getId(), p, n, tel);
                            manager.fermer();

                            if (res > 0) {
                                // Mettre à jour l'objet en mémoire pour rafraîchir la liste
                                c.setFirst(p);
                                c.setLast(n);
                                c.setPhone(tel);
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Modifié avec succès", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnBack.setOnClickListener(v -> dialog.dismiss());
                btnInit.setOnClickListener(v -> {
                    edNom.setText("");
                    edPrenom.setText("");
                    edNum.setText("");
                });

                dialog.show();
            }
        });

        // Action sur le bouton Delete avec Confirmation
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Voulez-vous vraiment supprimer " + c.getFirst() + " ?");
                
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 1. Ouvrir le manager et supprimer en base
                        ContactManager manager = new ContactManager(context);
                        manager.Ouvrir();
                        int res = manager.supprimer(c.getId());
                        manager.fermer();

                        if (res > 0) {
                            // 2. Supprimer de la liste affichée
                            contacts.remove(position);
                            // 3. Notifier l'adapter pour rafraîchir la vue
                            notifyDataSetChanged();
                            Toast.makeText(context, "Contact supprimé", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });

        // Action sur le bouton Appel
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + c.getPhone()));
                context.startActivity(intent);
            }
        });

        return l;
    }
}
