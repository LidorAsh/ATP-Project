package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;

public class Route
    {
        private Position location;
        private final LinkedList<Position> path;

        public Route(Position location, LinkedList<Position> path)
        {
            this.location = location;
            this.path = path;
        }

        public LinkedList<Position> getPath()
        {
            return this.path;
        }

        public void setLocation(Position loc)
        {
            this.location = loc;
        }

        public Position getLocation()
        {
            return this.location;
        }

        public void removeLocation()
            {
                path.remove();
            }

        public boolean Contains(Position p)
            {
                return path.contains(p);
            }
    }
