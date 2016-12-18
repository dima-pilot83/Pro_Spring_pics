package ua.kiev.prog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {

    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();
    protected List<Long> ad = new ArrayList<Long>();


    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty()) throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null) throw new PhotoNotFoundException();
        else return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null) throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
//---------------------------------------------------------------------------

    @RequestMapping(value = "/listall", method = RequestMethod.POST)
    public String viewAll(Model model) {

        ad.clear();
        model.addAttribute("photo_id", photos.keySet());

        for (long key : photos.keySet()) {
            ad.add(key);
        }

        for (int j = 0; j < ad.size(); j++) {
            if (ad.get(j) != null) {
                model.addAttribute("photo_id" + j + "", ad.get(j));
                model.addAttribute("name" + j + "", "column1");
            }

        }

        return "delete2";
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.POST)
    public String deleteAll(Model model, HttpServletRequest request, @RequestParam("column1") String[] column1) {

        for (int j = 0; j < column1.length; j++) {

            photos.remove(Long.valueOf(column1[j]));
            ad.remove(ad.indexOf(Long.valueOf(column1[j])));
        }

        for (int j = 0; j < photos.size(); j++) {

            if (ad.get(j) != null) {
                model.addAttribute("photo_id" + j + "", ad.get(j));
                model.addAttribute("name" + j + "", "column1");
            }
        }

        model.addAttribute("photo_id", photos.keySet());

        return "delete2";
    }
}

