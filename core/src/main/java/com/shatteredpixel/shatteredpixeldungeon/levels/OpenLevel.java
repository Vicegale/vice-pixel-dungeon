package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.watabou.utils.Random;

public class OpenLevel extends Level {

    private static final int SIZE = 100;
    @Override
	public String tilesTex() {
		return Assets.Environment.TILES_CAVES;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}
    @Override
    protected boolean build() {
        setSize(SIZE, SIZE);

        for(int i = 1; i < width()-1; i++)
        {
            for(int j = 1; j < height()-1; j++)
            {
                map[i * width() + j] = Terrain.EMPTY;
                if(i % 100 == 0) map[i * width() + j] = Terrain.EMPTY_DECO;
            }
        }
        for(int i = 2; i < SIZE-2; i++)
        {
            map[SIZE*2 + i] = Terrain.WATER; //top line
            map[SIZE*(SIZE-3)+i] = Terrain.WATER; //bottom line
            map[SIZE*(i) + 2] = Terrain.WATER; //left line
            map[SIZE*(i+1)-3] = Terrain.WATER; //right line
        }


        int x = Random.Int(1, width()-1);
        int y = Random.Int(1, height()-1);
        entrance = y * width() + x;
		map[entrance] = Terrain.ENTRANCE;
        
		
        for(int i = 0; i < Random.Int(1, 3); i++)
        {
            int exitX = Random.Int(1, width()-1);
            int exitY = Random.Int(1, height()-1);

            exit = exitY * width() + exitX;
            map[exit] = Terrain.EXIT;
        }

        return true;
    }

    @Override
    protected void createMobs() {
        // TODO Auto-generated method stub+
        int mobsToSpawn = Dungeon.depth == 1 ? 8 : mobLimit();

        for(int i = 0; i < mobsToSpawn; i++)
        {
            Mob mob = createMob();
            int x = Random.IntRange(2, SIZE-1);
            int y = Random.IntRange(2, SIZE-1);
            mob.pos = x*width() + y;
            mobs.add(mob);
        }
    }

    @Override
    protected void createItems() {
        // TODO Auto-generated method stub
        
    }
    
}
