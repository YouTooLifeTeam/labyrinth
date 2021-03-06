package com.youtoolife.labyrinth.utils;

import java.util.Random;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.chunk.Chunk.Exits;
import com.youtoolife.labyrinth.chunk.ChunkGenerator;

public class MazeGenerator {

	static Chunk maze[][];

	private MazeGenerator(int size) {
		maze = new Chunk[size][size];
		char[][] lab = (new Maze()).getMaze(size + 1);
		while (!check_maze(lab))
			lab = (new Maze()).getMaze(size + 1);

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (lab[i][j] == 1) {
					maze[i - 1][j - 1] = ChunkGenerator.getChunk(Exits.NoExit);
				} else {
					int exits = 4 - (lab[i - 1][j] + lab[i + 1][j]
							+ lab[i][j - 1] + lab[i][j + 1]);
					if (exits == 4) {
						maze[i - 1][j - 1] = ChunkGenerator
								.getChunk(Exits.QuadroExit);
					}
					if (exits == 3) {
						Chunk buf = ChunkGenerator.getChunk(Exits.TriExit);
						if (lab[i][j + 1] == 1)
							buf.rotateClockwise(1);
						if (lab[i][j - 1] == 1)
							buf.rotateClockwise(3);
						if (lab[i - 1][j] == 1)
							buf.rotateClockwise(2);
						maze[i - 1][j - 1] = buf;
					}
					if (exits == 1) {
						Chunk buf = ChunkGenerator.getChunk(Exits.SingleExit);
						if (lab[i][j - 1] == 0)
							buf.rotateClockwise(1);
						if (lab[i + 1][j] == 0)
							buf.rotateClockwise(2);
						if (lab[i][j + 1] == 0)
							buf.rotateClockwise(3);
						maze[i - 1][j - 1] = buf;
					}
					if (exits == 2) {
						Chunk buf = null;
						if (lab[i][j - 1] == 0 && lab[i][j + 1] == 0) {
							buf = ChunkGenerator.getChunk(Exits.DiOpposite);
							buf.rotateClockwise(1);
						}
						if (lab[i - 1][j] == 0 && lab[i + 1][j] == 0) {
							buf = ChunkGenerator.getChunk(Exits.DiOpposite);
						}
						if (buf == null) {
							buf = ChunkGenerator.getChunk(Exits.DiNeighbour);
							if (lab[i + 1][j] == 1 && lab[i][j - 1] == 1)
								buf.rotateClockwise(3);
							if (lab[i - 1][j] == 1 && lab[i][j + 1] == 1)
								buf.rotateClockwise(1);
							if (lab[i + 1][j] == 0 && lab[i][j + 1] == 0)
								buf.rotateClockwise(2);

						}
						maze[i - 1][j - 1] = buf;
					}
				}
			}
		}
	}

	private boolean check_maze(char[][] lab) {
		char[][] buf = new char[lab.length][];
		int single_count = 0;
		boolean searched = false;
		buf[0] = lab[0].clone();
		buf[lab.length-1] = lab[lab.length-1].clone();
		for (int i = 1; i < lab.length - 1; i++){
			for (int j = 1; j < lab.length - 1; j++) {
				int exits = 4 - (lab[i - 1][j] + lab[i + 1][j] + lab[i][j + 1] + lab[i - 1][j - 1]);
				if (exits == 1)
					single_count++;
			}
			buf[i] = lab[i].clone();
		}
		if (single_count < 2)
			return false;

		
		for (int i = 1; i < buf.length - 1; i++)
			for (int j = 1; j < buf.length - 1; j++)
				if (buf[i][j] == 0 && !searched) {
					searched = true;
					deep_search(buf, i, j);
				}

		for (int i = 1; i < buf.length - 1; i++)
			for (int j = 1; j < buf.length - 1; j++)
				if (buf[i][j] == 0)
					return false;

		return true;
	}

	private void deep_search(char[][] maze, int i, int j) {
		maze[i][j] = 2;
		if (maze[i][j - 1] == 0)
			deep_search(maze, i, j - 1);
		if (maze[i][j + 1] == 0)
			deep_search(maze, i, j + 1);
		if (maze[i + 1][j] == 0)
			deep_search(maze, i + 1, j);
		if (maze[i - 1][j] == 0)
			deep_search(maze, i - 1, j);
	}

	public static Chunk[][] getMaze(int size, int[] positions) {
		new MazeGenerator(size);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (MazeGenerator.maze[i][j].type == Exits.SingleExit) {
					positions[0] = j;
					positions[1] = i;
				}
		for (int i = size - 1; i >= 0; i--)
			for (int j = size - 1; j >= 0; j--)
				if (MazeGenerator.maze[i][j].type == Exits.SingleExit) {
					positions[2] = j;
					positions[3] = i;
				}

		return MazeGenerator.maze;
	}

	private static class Maze {
		static final int fullfill = 89;
		static final int wallshort = 70;

		int size;

		Random rand = new Random();
		char[][] m;
		int[][] r;
		int h = 0;

		int startx, starty;

		public char[][] getMaze(int size) {

			this.size = size;

			m = new char[size + 1][size + 1];
			r = new int[2][size / 2 * size / 2];

			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					m[i][j] = 0;

			for (int i = 0; i <= size; i++) {
				m[0][i] = 1;
				m[size][i] = 1;

				m[i][0] = 1;
				m[i][size] = 1;
			}

			initrandom();

			while (getrandom()) {

				if (m[starty][startx] == 1)
					continue;
				if (rand.nextInt(100) > fullfill)
					continue;
				int sx = 0, sy = 0;
				do {
					sx = rand.nextInt(3) - 1;
					sy = rand.nextInt(3) - 1;
				} while (sx == 0 && sy == 0 || sx != 0 && sy != 0);
				while (startx <= size && starty <= size
						&& m[starty][startx] == 0) {
					if (rand.nextInt(100) > wallshort) {
						m[starty][startx] = 1;
						break;
					}
					m[starty][startx] = 1;
					startx += sx;
					starty += sy;
					m[starty][startx] = 1;
					startx += sx;
					starty += sy;
				}
			}
			return m;
		}

		private void initrandom() {
			int j = 0;

			for (int y = 2; y < size; y += 2)
				for (int x = 2; x < size; x += 2) {
					r[0][j] = x;
					r[1][j] = y;
					j++;
				}
			h = j - 1;
		}

		private boolean getrandom() {

			int i = (new Random()).nextInt(h);

			startx = r[0][i];
			starty = r[1][i];

			r[0][i] = r[0][h];
			r[1][i] = r[1][h];

			return (--h) != 0;

		}
	}
}
