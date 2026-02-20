package ELFEKIHOns.truecaller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        //creation des views
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(R.layout.view_contact, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactRecylerAdapter.MyViewHolder holder, int position) {
        //remplissage des views
        Contact c = contacts.get(position);
        holder.tfirst.setText(c.getFirst());
        holder.tphone.setText(c.getPhone());
        holder.tlast.setText(c.getLast());


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tlast, tfirst, tphone;
        ImageView edit, delete, call;

        public MyViewHolder(@NonNull View v) {
            super(v);
            //recuperation
            tlast = v.findViewById(R.id.tvnom_contact);
            tfirst = v.findViewById(R.id.tvpseudo_contact);
            tphone = v.findViewById(R.id.tvnumero_contact);

            edit = v.findViewById(R.id.imageViewEdit_contact);
            delete = v.findViewById(R.id.imageViewDelete_contact);
            call = v.findViewById(R.id.imageViewCall_contact);


            //action sur les boutons
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
                    Contact c = contacts.get(getAbsoluteAdapterPosition());
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
                    Contact c = contacts.get(getAbsoluteAdapterPosition());
                    builder.setTitle("Confirmation");
                    builder.setMessage("Voulez-vous vraiment supprimer " + c.getFirst() + " ?");

                    builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int indice = getAbsoluteAdapterPosition();
                            // 1. Ouvrir le manager et supprimer en base
                            ContactManager manager = new ContactManager(context);
                            manager.Ouvrir();
                            int res = manager.supprimer(c.getId());
                            manager.fermer();

                            if (res > 0) {
                                // 2. Supprimer de la liste affichée
                                contacts.remove(indice);
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
// Action sur le bouton Appel
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact c = contacts.get(pos);

                        // L'intention pour l'appel DIRECT
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + c.getPhone()));

                        // On vérifie si la permission est bien accordée
                        if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            // Si OUI, on lance l'appel direct
                            context.startActivity(callIntent);
                        } else {
                            // Si NON, on affiche le message et on ouvre le clavier (fallback)
                            Toast.makeText(context, "Permission d'appel non accordée", Toast.LENGTH_SHORT).show();

                            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                            dialIntent.setData(Uri.parse("tel:" + c.getPhone()));
                            context.startActivity(dialIntent);
                        }
                    }
                }
            });
        }
    }
}
