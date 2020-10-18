package com.example.park.findroad;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


public class MapNode {
    private double x;
    private double y;
    private double G;
    private double F;
    private double H;
    private MapNode fatherNode;
    public boolean reachable;
    public int mark;

    public MapNode() {
    }

    public MapNode(double x, double y) {
        this.x = x;
        this.y = y;
        this.G = 0.0D;
        this.F = 0.0D;
        this.H = 0.0D;
        this.fatherNode = null;
        this.reachable = true;
        this.mark = 1;
    }

    public String toString() {
        return "MapNode [x=" + this.x + ", y=" + this.y + ", reachable=" + this.reachable + "]";
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isReachable() {
        return this.reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public int ismark() {
        return this.mark;
    }

    public void setmark(int mark) {
        this.mark = mark;
    }

    public MapNode getFatherNode() {
        return this.fatherNode;
    }

    public void setFatherNode(MapNode fatherNode) {
        this.fatherNode = fatherNode;
        this.F = fatherNode.F + this.distanceTo(fatherNode);
        this.G = this.F + this.H;
    }

    public double calH(MapNode fatherNode) {
        double distance = 0.0D;
        if (fatherNode.x == this.x) {
            distance = Math.abs(fatherNode.y - this.y);
        } else if (fatherNode.y == this.y) {
            distance = Math.abs(fatherNode.x - this.x);
        } else {
            distance = 1.414D * Math.abs(fatherNode.y - this.y);
        }

        return fatherNode.H + distance;
    }

    public double distanceTo(MapNode to) {
        return Math.sqrt((this.x - to.x) * (this.x - to.x) + (this.y - to.y) * (this.y - to.y));
    }

    public void setH(MapNode node) {
        this.H = this.distanceTo(node);
    }

    public double getH() {
        return this.H;
    }

    public double getG() {
        return this.G;
    }

    public int hashCode() {
        int prime = 1;
        int result = 1;
        long temp = Double.doubleToLongBits(this.x);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.y);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            MapNode other = (MapNode)obj;
            if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
                return false;
            } else {
                return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
            }
        }
    }
}

