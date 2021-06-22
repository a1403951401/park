module.exports = {
  publicPath: './',
  pwa: {
    iconPaths: {
      favicon32: 'favicon.ico',
      favicon16: 'favicon.ico',
      appleTouchIcon: 'favicon.ico',
      maskIcon: 'favicon.ico',
      msTileImage: 'favicon.ico',
    },
  },
  // 所有 webpack-dev-server 的选项都支持。
  devServer: {
    host: 'localhost',
    port: 8000, // 端口号
    open: false, //配置自动启动浏览器

    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080',
        pathRewrite: {
          '^/': '',
        },
      },
    },
  },
};
