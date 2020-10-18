package com.example.park.findroad;


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.Deque;
import java.util.HashSet;

public class Map {
    private double nodeSize;
    private int xScale;
    private int yScale;
    private double startX;
    private double startY;
    private MapNode[][] mapNodes;
    public double[][] count_path = new double[50][50];
    public double sum_path = 0.0D;

    public Map() {
    }

    public Map(int xScale, int yScale, double nodeSize, double startX, double startY) {
        this.mapNodes = new MapNode[yScale][xScale];
        this.nodeSize = nodeSize;
        this.xScale = xScale;
        this.yScale = yScale;
        this.startX = startX;
        this.startY = startY;
        double y = startY;

        for(int i = 0; i < yScale; ++i) {
            double x = startX;

            for(int j = 0; j < xScale; ++j) {
                this.mapNodes[i][j] = new MapNode(x, y);
                x += nodeSize;
            }

            y += nodeSize;
        }

    }

    public void print() {
        for(int i = 0; i < this.yScale; ++i) {
            for(int j = 0; j < this.xScale; ++j) {
                if (this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 1) {
                    System.out.print("□");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 3) {
                    System.out.print("■");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 2) {
                    System.out.print("▲");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 4) {
                    System.out.print("▲");
                }
            }

            System.out.println();
        }

    }

    public void print(Deque<MapNode> result) {
        for(int i = 0; i < this.yScale; ++i) {
            for(int j = 0; j < this.xScale; ++j) {
                if (result.contains(this.mapNodes[i][j])) {
                    System.out.print("*");
                } else if (this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 1) {
                    System.out.print("□");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 3) {
                    System.out.print("■");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 2) {
                    System.out.print("▲");
                } else if (!this.mapNodes[i][j].isReachable() && this.mapNodes[i][j].mark == 4) {
                    System.out.print("▲");
                }
            }

            System.out.println();
        }

    }

    public void Sum_path(Deque<MapNode> result) {
        this.sum_path = 0.0D;

        for(int i = 0; i < this.yScale; ++i) {
            for(int j = 0; j < this.xScale; ++j) {
                if (result.contains(this.mapNodes[i][j])) {
                    ++this.sum_path;
                    this.count_path[i][j] = this.sum_path;
                }
            }
        }

    }

    public void editObstacle(int[][] message) {
        for(int i = 0; i < this.yScale; ++i) {
            for(int j = 0; j < this.xScale; ++j) {
                if (message[i][j] == 2 || message[i][j] == 4) {
                    this.mapNodes[i][j].mark = 2;
                    this.mapNodes[i][j].reachable = false;
                }

                if (message[i][j] == 3) {
                    this.mapNodes[i][j].mark = 3;
                    this.mapNodes[i][j].reachable = false;
                }
            }
        }

    }

    public MapNode XY2MapNode(double x, double y) {
        int j = (int)((x - this.startX) / this.nodeSize);
        int i = (int)((y - this.startY) / this.nodeSize);
        return j < this.xScale && i < this.yScale && j >= 0 && i >= 0 ? this.mapNodes[i][j] : null;
    }

    public MapNode node2MapNode(MapNode node) {
        return this.XY2MapNode(node.getX(), node.getY());
    }

    public int getXIndex(MapNode node) {
        return (int)((node.getX() - this.startX) / this.nodeSize);
    }

    public int getYIndex(MapNode node) {
        return (int)((node.getY() - this.startY) / this.nodeSize);
    }

    public HashSet<MapNode> getNeighbors(MapNode node) {
        HashSet<MapNode> neighbors = new HashSet();
        int x = this.getXIndex(node);
        int y = this.getYIndex(node);

        for(int i = -1; i <= 1; ++i) {
            if (y + i < this.yScale && y + i >= 0) {
                for(int j = -1; j <= 1; ++j) {
                    if ((i != 0 || j != 0) && x + j < this.xScale && x + j >= 0 && this.mapNodes[y + i][x + j].isReachable() && (i * j != -1 && i * j != 1 || this.mapNodes[y + i][x].isReachable() && this.mapNodes[y][x + j].isReachable())) {
                        neighbors.add(this.mapNodes[y + i][x + j]);
                    }
                }
            }
        }

        return neighbors;
    }

    public void change(int des0, int des1) {
        this.mapNodes[des1][des0].reachable = true;
    }
}
