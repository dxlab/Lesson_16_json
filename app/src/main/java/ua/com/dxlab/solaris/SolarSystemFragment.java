package ua.com.dxlab.solaris;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ua.com.dxlab.solaris.models.PlanetModel;
import ua.com.dxlab.solaris.models.SkyModel;
import ua.com.dxlab.solaris.models.SolarSystemModel;
import ua.com.dxlab.solaris.views.SolarSystemData;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolarSystemFragment extends Fragment implements SolarSystemCallbackLoading {

    private SolarSystemModel mSolarSystemModel;
    private ExpandableListView mSolarSystemList;
    private SolarSystemAdapter mAdapter;
    private TextView mSpotHeader;


    public SolarSystemFragment() {
        // Required empty public constructor
        this.setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_solar_system, container, false);
        findViews(v);

        //TODO would check whether is device connected!!!

        new SolarSystemAsyncJsonLoader(this, getActivity()).execute();
        return v;

    }

    /**
     * inits current fragment UI
     */
    private void findViews(View v){
        mSolarSystemList    = (ExpandableListView) v.findViewById(R.id.expandListView_FSS);
        mSpotHeader     = (TextView) v.findViewById(R.id.tvSpot_FSS);
    }

    @Override
    public void onSuccess(SolarSystemModel solarSystemModel) {
        Log.d("onSuccess", "Success");
        mSolarSystemModel = solarSystemModel;
        Log.d("onSuccess", "Success1");
        setSolarSystemModel();
        mSolarSystemList.setAdapter(mAdapter);
    }
    private void setSolarSystemModel() {

        mSpotHeader.setText(mSolarSystemModel.spot.getInfoText());

        ArrayList<ArrayList<SolarSystemData>> solarSystemData = new ArrayList<>();
        ArrayList<SolarSystemData> planets = new ArrayList<>();
        ArrayList<SolarSystemData> dwarfs = new ArrayList<>();
        ArrayList<SolarSystemData> comets = new ArrayList<>();


        //TODO incapsulate this to universal method working with SkyModel
        for (int i=0; i<mSolarSystemModel.planets.size();i++) {
            planets.add(new SolarSystemData(mSolarSystemModel.planets.get(i).getImgUrl(), mSolarSystemModel.planets.get(i).getInfoText()));
        }
        solarSystemData.add(planets);

        for (int i=0; i<mSolarSystemModel.comets.size();i++) {
            comets.add(new SolarSystemData(mSolarSystemModel.comets.get(i).getImgUrl(), mSolarSystemModel.comets.get(i).getInfoText()));
        }
        solarSystemData.add(comets);

        for (int i=0; i<mSolarSystemModel.dwarfs.size();i++) {
            dwarfs.add(new SolarSystemData(mSolarSystemModel.dwarfs.get(i).getImgUrl(), mSolarSystemModel.dwarfs.get(i).getInfoText()));
        }
        solarSystemData.add(dwarfs);


        mAdapter = new SolarSystemAdapter(getActivity(), solarSystemData);
    }


    @Override
    public void onFailure(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }


}
