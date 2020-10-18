package com.example.park.findroad;


import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class AstarPathPlan {
    public Map map;
    private HashSet<MapNode> closeList;
    private HashSet<MapNode> openList;

    public AstarPathPlan(int xScale, int yScale, double nodeSize, double startX, double startY) {
        this.map = new Map(xScale, yScale, nodeSize, startX, startY);
        this.closeList = new HashSet();
        this.openList = new HashSet();
    }

    public LinkedList<MapNode> pathPlanning(MapNode from, MapNode to) {
        this.openList.clear();
        this.closeList.clear();
        to = this.map.node2MapNode(to);
        from = this.map.node2MapNode(from);
        if (from.isReachable() && to.isReachable() || this.map.node2MapNode(from) != null && this.map.node2MapNode(to) != null) {
            this.openList.add(from);

            while(!this.openList.isEmpty()) {
                MapNode currentNode = this.getBestNode(this.openList);
                HashSet<MapNode> neighbors = this.map.getNeighbors(currentNode);
                if (neighbors.contains(to)) {
                    to.setFatherNode(currentNode);
                    break;
                }

                Iterator var6 = neighbors.iterator();

                while(var6.hasNext()) {
                    MapNode neighbor = (MapNode)var6.next();
                    if (!this.closeList.contains(neighbor)) {
                        neighbor.setH(to);
                        if (this.openList.contains(neighbor)) {
                            MapNode n = this.getNodeFromList(neighbor, this.openList);
                            double H = neighbor.calH(currentNode);
                            if (H < n.getH()) {
                                neighbor.setFatherNode(currentNode);
                                this.openList.add(neighbor);
                            }
                        } else {
                            neighbor.setFatherNode(currentNode);
                            this.openList.add(neighbor);
                        }
                    }
                }

                this.closeList.add(currentNode);
                this.openList.remove(currentNode);
            }

            return this.getResult(from, to);
        } else {
            return null;
        }
    }

    public LinkedList<MapNode> getResult(MapNode from, MapNode to) {
        LinkedList<MapNode> result = new LinkedList();

        for(MapNode node = to; !node.equals(from); node = node.getFatherNode()) {
            if (node.getFatherNode() == null) {
                return null;
            }

            result.push(node);
        }

        return result;
    }

    public MapNode getBestNode(HashSet<MapNode> set) {
        MapNode bestNode = null;
        double bestG = 1.7976931348623157E308D;
        Iterator var6 = set.iterator();

        while(var6.hasNext()) {
            MapNode node = (MapNode)var6.next();
            if (node.getG() < bestG) {
                bestG = node.getG();
                bestNode = node;
            }
        }

        return bestNode;
    }

    public MapNode getNodeFromList(MapNode node, HashSet<MapNode> openList) {
        Iterator var4 = openList.iterator();

        while(var4.hasNext()) {
            MapNode n = (MapNode)var4.next();
            if (n.equals(node)) {
                return n;
            }
        }

        return null;
    }

    public void printResult(Deque<MapNode> result) {
        if (result != null) {
            this.map.print(result);
        }
    }

    public void printSumpath(Deque<MapNode> result) {
        if (result != null) {
            this.map.Sum_path(result);
        }
    }

    public void change_A(int des0, int des1) {
        this.map.change(des0, des1);
    }
}




