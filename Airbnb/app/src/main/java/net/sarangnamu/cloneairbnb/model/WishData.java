package net.sarangnamu.cloneairbnb.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by aucd29 on 2016-03-24.
 */
public class WishData extends RealmObject {
    @Required
    private String name;
    private byte[] image;
    private int id;
}
