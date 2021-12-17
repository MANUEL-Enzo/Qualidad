package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.util.ArrayList;
import java.util.List;

public class Set {
    private SideType sideType;
    private final List<Entity> entities;
    private final List<EntityType> entityTypes;

    public Set(SideType sideType) {
        this.sideType = sideType;
        this.entities = new ArrayList<>();
        this.entityTypes = new ArrayList<>();
    }

    public void add(EntityType entityType, Entity entity) {
        entities.add(entity);
        entityTypes.add(entityType);
    }


    public void setSideType(SideType sideType) {
        this.sideType = sideType;
    }

    public List<EntityType> getEntityTypes() {
        return entityTypes;

    }

    public boolean contain(EntityType entityType) {
        return entityTypes.contains(entityType);
    }

    public Entity get(int index) {
        return entities.get(index);
    }

    public int size() {
        return entities.size();
    }

    public SideType getSideType() {
        return sideType;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0 ; i < getEntityTypes().size() ; i++){
            s+= " " + getEntityTypes().get(i).toString();
        }
        return s;
    }
}