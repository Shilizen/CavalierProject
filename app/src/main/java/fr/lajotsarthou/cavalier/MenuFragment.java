package fr.lajotsarthou.cavalier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class MenuFragment extends Fragment {
    private UserModele user;
    private SharedPreferences preferences;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        user = new ViewModelProvider(requireActivity()).get(UserModele.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menuhamburger, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String nom = preferences.getString("nom", "");
        int id = item.getItemId();

        switch (id) {
            case R.id.mLogin:
                if(!nom.equals("")) {
                    Intent navProfile = new Intent(MenuFragment.this.getActivity(), ProfilActivity.class);
                    startActivity(navProfile);
                    return true;
                } else {
                    Intent navLogin = new Intent(MenuFragment.this.getActivity(), LoginActivity.class);
                    startActivity(navLogin);
                    return true;
                }

            case R.id.mRechercherClub:
                Intent navRechC = new Intent(MenuFragment.this.getActivity(), ClubActivity.class);
                startActivity(navRechC);
                return true;

            case R.id.mAccueil:
                Intent navAcc = new Intent(MenuFragment.this.getActivity(), AccueilActivity.class);
                startActivity(navAcc);
                return true;

            case R.id.mRechercherEquide:
                Intent navEquide = new Intent(MenuFragment.this.getActivity(), EquideActivity.class);
                startActivity(navEquide);
                return true;

            case R.id.mEngagement:
                if(!nom.equals("")) {
                    Intent navEng = new Intent(MenuFragment.this.getActivity(), EngagementActivity.class);
                    startActivity(navEng);
                    return true;
                } else {
                    Intent navLoginEng = new Intent(MenuFragment.this.getActivity(), LoginActivity.class);
                    startActivity(navLoginEng);
                    return false;
                }
        }
        return false;
    }
}