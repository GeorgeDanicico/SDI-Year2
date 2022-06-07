package factory.serverapp.model;

import org.json.JSONObject;

public class Sensor {
    private int id;
    private String name;
    private Double measurement;

    public Sensor(int _id, String _name, Double _measurement) {
        id = _id;
        name = _name;
        measurement = _measurement;
    }

    public JSONObject encode() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("measurement", measurement);

        return jsonObject;
    }

    public static Sensor decode(JSONObject sensorobj) {
        int id = sensorobj.getInt("id");
        String name = sensorobj.getString("name");
        Double measurement = sensorobj.getDouble("measurement");

        return new Sensor(id, name, measurement);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMeasurement() {
        return measurement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasurement(Double measurement) {
        this.measurement = measurement;
    }
}
