package pm.omnisearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search {
    private String make;
    private String model;
    private String bodyType;
    private String fuel;
    private String colour;
    private List<String> other;

    private Search(String make, String model, String bodyType, String fuel, String colour, List<String> other) {
        this.make = make;
        this.model = model;
        this.bodyType = bodyType;
        this.fuel = fuel;
        this.colour = colour;
        this.other = other;
    }

    static Builder builder() {
        return new Builder();
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getFuel() {
        return fuel;
    }

    public String getColour() {
        return colour;
    }

    public List<String> getOther() {
        return other;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, bodyType, fuel, colour, other);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Search other = (Search) obj;
        return Objects.equals(this.make, other.make)
                && Objects.equals(this.model, other.model)
                && Objects.equals(this.bodyType, other.bodyType)
                && Objects.equals(this.fuel, other.fuel)
                && Objects.equals(this.colour, other.colour)
                && Objects.equals(this.other, other.other);
    }

    @Override
    public String toString() {
        return "Search{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", fuel='" + fuel + '\'' +
                ", colour='" + colour + '\'' +
                ", other=" + other +
                '}';
    }

    static class Builder {
        private String make;
        private String model;
        private String bodyType;
        private String fuel;
        private String colour;
        private List<String> other;

        private Builder() {
            other = new ArrayList<>();
        }

        Builder withMake(String make) {
            this.make = make;
            return this;
        }

        Builder withModel(String model) {
            this.model = model;
            return this;
        }

        Builder withBodyType(String bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        Builder withFuel(String fuel) {
            this.fuel = fuel;
            return this;
        }

        Builder withColour(String colour) {
            this.colour = colour;
            return this;
        }

        Builder withOther(String other) {
            this.other.add(other);
            return this;
        }

        Search build() {
            return new Search(make, model, bodyType, fuel, colour, other);
        }
    }
}
