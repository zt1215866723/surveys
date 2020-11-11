package com.lfxwkj.sur.util;

import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: survey
 * @description:
 * @author: zt
 * @create: 2020-11-09 11:02
 **/
@Component
public class TakeFilePathAndName {
    public static void main(String[] args) {
        //这是需要获取的文件夹路径
        String path = "C:/Users/lenovo/Desktop/新建文件夹";
        getFile(path, 0);
    }

    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    public static List<LayuiTreeNode> getFile(String path, int deep) {

        List<LayuiTreeNode> layuiTreeNodeList = new ArrayList<>();

        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for (int i = 0; i < array.length; i++) {
            LayuiTreeNode layuiTreeNode = new LayuiTreeNode();
            layuiTreeNode.setTitle(array[i].getName());
            layuiTreeNode.setId((long) i);
            layuiTreeNode.setSpread(false);
            layuiTreeNode.setHref(array[i].getPath());
            layuiTreeNodeList.add(layuiTreeNode);
            if (array[i].isFile())//如果是文件
            {
//                System.out.print(" ");
                // 只输出文件名字
//                System.out.println( array[i].getName());
                // 输出当前文件的完整路径
//                System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                // System.out.println(array[i].getPath());
            } else if (array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                System.out.print(" ");
//                System.out.println(array[i].getName());
//                System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                    List<LayuiTreeNode> file1 = getFile(array[i].getPath(), deep + 1);
                    layuiTreeNode.setChildren(file1);
            }
        }
        return layuiTreeNodeList;
    }
}
