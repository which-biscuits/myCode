import tornado.ioloop
import tornado.web

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("newlife.html")
    def post(self):
        a = self.get_argument("xingming")
        return_data = {"xingming":a}
        self.write(return_data)

if __name__ == "__main__":
    application = tornado.web.Application([
        (r"http://106.14.245.132/", MainHandler),
    ])
    application.listen(80)
    tornado.ioloop.IOLoop.current().start()