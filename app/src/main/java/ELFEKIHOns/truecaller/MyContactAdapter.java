package ELFEKIHOns.truecaller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.Buffer;
import java.util.ArrayList;

public class MyContactAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> contacts;
    MyContactAdapter(Context context, ArrayList<Contact> contacts){
        this.context=context;
        this.contacts=contacts;
    }
    @Override
    public int getCount() {
        //le nombre de vue à créer
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //le view à créer
        //creation d'un view
        LayoutInflater inf=LayoutInflater.from(context);
        View l=inf.inflate(R.layout.view_contact,null);
        //récupération des composants
        TextView tlast=l.findViewById(R.id.tvnom_contact);
        TextView tfirst=l.findViewById(R.id.tvpseudo_contact);
        TextView tphone=l.findViewById(R.id.tvnumero_contact);


        ImageView call=l.findViewById(R.id.imageViewCall_contact);
        ImageView edit=l.findViewById(R.id.imageViewEdit_contact);
        ImageView delete=l.findViewById(R.id.imageViewDelete_contact);

        //affectaion holders

        Contact c=contacts.get(position);
        tfirst.setText(c.getFirst());
        tphone.setText(c.getPhone());
        tlast.setText(c.getLast());
        return l;
    }
}
