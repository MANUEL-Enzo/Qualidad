package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CanonEntity.class, name = "canon"),
        @JsonSubTypes.Type(value = OarEntity.class, name = "oar"),
        @JsonSubTypes.Type(value = RudderEntity.class, name = "rudder"),
        @JsonSubTypes.Type(value = SailEntity.class, name = "sail"),
        @JsonSubTypes.Type(value = WatchEntity.class, name = "watch")
})
public abstract class Entity {
    protected final EntityType type;
    protected final Coordinate coordinate;

    protected SideType sideType;
    private boolean isAssigned;

    @JsonCreator
    protected Entity(@JsonProperty("type") String type, @JsonProperty("x") int x, @JsonProperty("y") int y) {
        this.type = EntityType.get(type);
        this.coordinate = new Coordinate(x, y);
        this.sideType = SideType.NOT_ASSIGNED;
        this.isAssigned = false;
    }

    public void assignSide(SideType sideType) {
        this.sideType = sideType;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void assign() {
        this.isAssigned = true;
    }

    public boolean isEntity(EntityType entityType) {
        return type.equals(entityType);
    }

    public boolean isSide(SideType sideType) {
        return this.sideType.equals(sideType);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public SideType getSideType() {
        return sideType;
    }

    public EntityType getEntityType() {
        return type;
    }


}