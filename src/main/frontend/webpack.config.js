var CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
    entry: [
        './src/index.js'
    ],
    devtool: 'source-map',
    output: {
        path: __dirname + '/../resources/public',
        publicPath: '/',
        filename: 'bundle.js'
    },
    plugins: [
        new CopyWebpackPlugin([
            {from: 'assets/**/*'},
            {from: 'index.html'}
        ])
    ],
    module: {
        loaders: [{
            exclude: /node_modules/,
            loader: 'babel-loader',
            query: {
                presets: ['react', 'env']
            }
        }]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    }
};
