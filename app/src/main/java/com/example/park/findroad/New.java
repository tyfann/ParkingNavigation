package com.example.park.findroad;


import android.util.Log;

import com.example.park.fragment.InputMap;

import java.util.Deque;
import java.util.List;

public class New {
    public static int[][] message = new int[50][50];
    public static int parking_length = 0;
    public static int parking_width = 0;
    public static int[] des = new int[3];

    public New() {
    }

    public static void show_message() {
        for(int i = 0; i < message.length; ++i) {
            for(int j = 0; j < message[i].length; ++j) {
                if (message[i][j] != 0) {
                    System.out.println("i:" + i + ",j:" + j + ",值" + message[i][j]);
                }
            }
        }

    }

    public static void simulate() {
        int i;
        for(i = 0; i < message.length; ++i) {
            if (message[0][i] != 0) {
                ++parking_width;
            } else if (message[0][i] == 0) {
                break;
            }
        }

        for(i = 0; i < message.length; ++i) {
            if (message[i][0] != 0) {
                ++parking_length;
            } else if (message[i][0] == 0) {
                break;
            }
        }

    }

    public static void Sort(AstarPathPlan a) {
        double minn = 0.0D;
        int temp1 = 0;
        int temp2 = 0;

        int i;
        int j;
        for(i = 0; i < parking_length; ++i) {
            for(j = 0; j < parking_width; ++j) {
                if (a.map.count_path[i][j] != 0.0D) {
                    minn = a.map.count_path[i][j];
                    break;
                }
            }
        }

        des[2] = (int)minn;

        for(i = 0; i < parking_length; ++i) {
            for(j = 0; j < parking_width; ++j) {
                if (a.map.count_path[i][j] < minn && message[i][j] == 2) {
                    minn = a.map.count_path[i][j];
                    temp1 = i;
                    temp2 = j;
                }
            }
        }

        des[0] = temp1;
        des[1] = temp2;
    }

    public static void PrintParkingPath(List<InputMap> inputMap,char selection)
    {
        AstarPathPlan astarPathPlan = null;

        for(int i=0;i<200;i++)
        {
            message[inputMap.get(i).getX()][inputMap.get(i).getY()]=inputMap.get(i).getValues();
        }

        simulate();


        if(selection=='A')
        {
            astarPathPlan=new AstarPathPlan(parking_width,parking_length,1,0,0);
            astarPathPlan.map.editObstacle(message);
            astarPathPlan.map.print();

        }
        else if(selection=='B')
        {
            astarPathPlan=new AstarPathPlan(parking_width,parking_length,1,19,9);
            astarPathPlan.map.editObstacle(message);
            astarPathPlan.map.print();
        }


        for(int i=0;i<parking_length;i++)
        {
            for(int j=0;j<parking_width;j++)
            {
                Deque<MapNode> result2 =astarPathPlan.pathPlanning(new MapNode(0,0), new MapNode(j,i));
                astarPathPlan.printSumpath(result2);
                double temp2=astarPathPlan.map.count_path[i][j];
            }
        }

        Sort(astarPathPlan);

        astarPathPlan.change_A(des[1], des[0]);
        Deque<MapNode>result3=astarPathPlan.pathPlanning(new MapNode(0,0), new MapNode(des[1],des[0]));
        astarPathPlan.printResult(result3);
        double temp3=astarPathPlan.map.count_path[des[0]-1][des[1]];

        System.out.println(temp3);
    }
/*
    public int[] PrintParkingPath(List<InputMap> var1, char var2) {
        throw new Error("无法解析的编译问题：\n\tList 无法解析为类型\n\tInputMap 无法解析为类型\n\t无法解析 inputmap\n\t无法解析 inputmap\n\t无法解析 inputmap\n\t无法解析 a\n\t无法解析 a\n\ta 无法解析为变量\n\ta 无法解析为变量\n\t无法解析 a\n\t无法解析 a\n\t无法解析 a\n\ta 无法解析为变量\n");
    }

 */
}

