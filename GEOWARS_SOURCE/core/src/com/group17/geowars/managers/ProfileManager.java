package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.playerobjects.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 10/11/2016.
 */
public class ProfileManager {


    private List<Profile> profiles;
    public List<Profile> getProfiles()
    {
        return profiles;
    }

    public ProfileManager()
    {
        profiles = new ArrayList<Profile>();
    }

    public void addProfile(Profile p)
    {
        profiles.add(p);
    }

    public void init()
    {
    }

    public void update()
    {
        for (Profile p: profiles) {
            p.getPlayer().update();
        }
    }
    public void render(Batch batch)
    {
        for (Profile p: profiles) {
            p.getPlayer().render(batch);
        }
    }
}
