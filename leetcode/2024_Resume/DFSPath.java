
Find the top left and bottom right coordinates of a rectangle of 0's within a matrix of 1's. It's essentially a modified version of the finding the number of island problem where you only need to dfs to the right and down.
Ex.
[[ 1, 1, 1, 1],
[ 1, 0, 0, 1],
[ 1, 0, 0, 1],
[ 1, 1, 1, 1]]
Expected output: [[1,1], [2,2]]

Follow up question: Expand it so it works for any number of rectangles. I ran out of time to code this part so get through the first part quickly. Main part of this problem is updating how results are stored and tracking what's been seen.
Ex.
[[0, 1, 1, 1],
[1, 0, 0, 1],
[1, 0, 0, 1],
[1, 1, 1, 1]]
Expected output: [ [[0,0],[0,0]], [[1,1], [2,2]] ]

// Only difference between part 1 and part 2 is marking visited in 2nd part and returning res at the end instead of immediately returning like in 1st part

// Part 1:
vector<vector<int>> helper(vector<vector<int>> &board)
{
    int m = board.size(), n = board[0].size();
    vector<vector<int>> res;

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (board[i][j] == 0)
            {
                res.push_back({i, j});
                int height = 1, width = 1;
                while (i + height < m && board[i + height][j] == 0)
                    height++;
                while (j + width < n && board[i][j + width] == 0)
                    width++;

                res.push_back({i + height - 1, j + width - 1});
                return res; // Immedeately returning
            }
        }
    }

    return {};
}

int main()
{
    vector<vector<int>> board = {
        {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0},
        {1, 0, 0, 1, 0},
        {1, 1, 1, 1, 0}};

    vector<vector<int>> res = helper(board);

    for (auto &row : res)
    {
        for (auto &val : row)
        {
            cout << val << " ";
        }
        cout << endl;
    }
}

// Part 2:
vector<vector<int>> helper(vector<vector<int>> &board)
{
    int m = board.size(), n = board[0].size();
    vector<vector<int>> res;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (board[i][j] == 0)
            {

                // Push start coordinates
                res.push_back({i, j});
                int height = 1, width = 1;
                while (i + height < m && board[i + height][j] == 0)
                    height++;
                while (j + width < n && board[i][j + width] == 0)
                    width++;

                // Mark current rectangle as visited.
                for (int h = 0; h < height; h++)
                {
                    for (int w = 0; w < width; w++)
                    {
                        board[i + h][j + w] = 1;
                    }
                }

                // Push end coordinates
                res.push_back({i + height - 1, j + width - 1});
            }
        }
    }
    return res; // return at the end
}