package com.myhomework;

import java.util.Arrays;

public class FindContentChildren {
    public int findContentChildren(int[] grid, int[] size) {
        if (grid == null || size == null) return 0;
        Arrays.sort(grid);
        Arrays.sort(size);
        //将胃口和孩子可以分配的饼干数量排序，胃口和饼干分配按照从小到大
        //因为将最小的饼干分配给最小的胃口，每次都这么做，可以保证最终是最优解，所以适合贪心
        int gi = 0, si = 0;
        while (gi < grid.length && si < size.length) {
            if (grid[gi] <= size[si]) {
                //当饼干数量可以满足该小孩的胃口时，则将该饼干分配给该小孩
                //然后移动到下一个更大胃口的小孩
                gi++;
            }
            //如果该饼干无法满足该小孩的胃口，则移动到下一个更大尺寸的饼干尝试满足
            si++;
        }
        return gi;
    }

}
