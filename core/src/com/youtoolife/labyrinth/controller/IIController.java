package com.youtoolife.labyrinth.controller;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Unit;

public class IIController extends Controller {

	@Override
	public void update() {
	}

	public void update(Chunk chunk, Unit mob) {
		/*
		 * int a = MathUtils.random(3); Action e = Action.Up; if(a==0) e =
		 * Action.Down; if(a==1) e = Action.Left; if(a==2) e = Action.Right;
		 * this.queue.add(e);
		 */

		int[][] map = new int[Chunk.SIZE][Chunk.SIZE];
		int max = Chunk.SIZE * Chunk.SIZE * Chunk.SIZE;

		for (int i = 0; i < Chunk.SIZE; i++)
			for (int j = 0; j < Chunk.SIZE; j++)
				if (chunk.map[i][j].canStep())
					map[i][j] = max;
				else
					map[i][j] = 0;

		map[Chunk.SIZE - 1 - GamePlayState.player1.y][GamePlayState.player1.x] = Chunk.SIZE
				* Chunk.SIZE * Chunk.SIZE;
		map[Chunk.SIZE - 1 - GamePlayState.player2.y][GamePlayState.player2.x] = Chunk.SIZE
				* Chunk.SIZE * Chunk.SIZE;
		map[Chunk.SIZE - mob.y - 1][mob.x] = 1;

		map = waveAlg(map);

		int length1 = map[Chunk.SIZE - 1 - GamePlayState.player1.y][GamePlayState.player1.x];
		int length2 = map[Chunk.SIZE - 1 - GamePlayState.player2.y][GamePlayState.player2.x];

		int tx = length1 > length2 ? GamePlayState.player2.x
				: GamePlayState.player1.x;
		int ty = length1 > length2 ? Chunk.SIZE - 1 - GamePlayState.player2.y
				: Chunk.SIZE - 1 - GamePlayState.player1.y;

		Action a = Action.NormAction;

		int step = map[ty][tx];
		while (step != 1) {
			if (ty > 0)
				if (map[ty - 1][tx] == step - 1) {
					ty--;
					step--;
					a = Action.Down;
					continue;
				}
			if (ty < Chunk.SIZE)
				if (map[ty + 1][tx] == step - 1) {
					ty++;
					step--;
					a = Action.Up;
					continue;
				}
			if (tx > 0)
				if (map[ty][tx - 1] == step - 1) {
					tx--;
					step--;
					a = Action.Right;
					continue;
				}
			if (tx < Chunk.SIZE)
				if (map[ty][tx + 1] == step - 1) {
					tx++;
					step--;
					a = Action.Left;
					continue;
				}
		}

		queue.add(a);

	}

	private int[][] waveAlg(int[][] a) {
		int step = 1;
		boolean isMoved = true;
		while (isMoved) {
			isMoved = false;
			for (int i = 1; i < Chunk.SIZE - 1; i++)
				for (int j = 1; j < Chunk.SIZE - 1; j++)
					if (a[i][j] == step) {
						if (a[i - 1][j] > step + 1) {
							a[i - 1][j] = step + 1;
							isMoved = true;
						}
						if (a[i + 1][j] > step + 1) {
							a[i + 1][j] = step + 1;
							isMoved = true;
						}
						if (a[i][j - 1] > step + 1) {
							a[i][j - 1] = step + 1;
							isMoved = true;
						}
						if (a[i][j + 1] > step + 1) {
							a[i][j + 1] = step + 1;
							isMoved = true;
						}

					}
			if (isMoved)
				step++;
		}
		
		for (int i = 0; i < Chunk.SIZE; i++){
			for (int j = 0; j < Chunk.SIZE; j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
		
		return a;
	}

}
