package com.example.proapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proapp.models.Job;
import com.example.proapp.models.Profession;
import com.example.proapp.utils.ObjectBox;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Profession>> professions;
    private MutableLiveData<List<Job>> jobs;



    private MutableLiveData<Long> selected = new MutableLiveData<>();


    public HomeViewModel() {

    }

    public LiveData<String> getText() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        return mText;
    }

    public LiveData<List<Profession>> getProfessions(){
        if(professions == null){
            professions = new MutableLiveData<>();
            loadProfessions();
        }

        return professions;
    }

    public LiveData<List<Job>> getJobs(){

        if(jobs == null){
            jobs = new MutableLiveData<>();
            getJobList();
        }

        return jobs;
    }


    public void setSelected(long id) {
        selected.setValue(id);
    }

    public LiveData<Long> getSelected(){
        return selected;
    }

    private void getJobList() {
        jobs.setValue(ObjectBox.get().boxFor(Job.class).getAll());
    }

    private void loadProfessions() {
        professions.setValue(ObjectBox.get().boxFor(Profession.class).getAll());
    }


}