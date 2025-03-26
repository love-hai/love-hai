package com.LoveSea.fengCore.downloader;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */
@Slf4j
public class FileExtensionMIMEResource {
    // 从浏览器下载文件不常用 ，若有使用弱引用
    private static long ID_CARD = 2093631819L;

    private FileExtensionMIMEResource() {
        log.info("{} init", ID_CARD);
    }

    public static WeakReference<FileExtensionMIMEResource> INSTANCE;

    public static FileExtensionMIME getByExtension(String extension) {
        return getInstance().getByExtension_(extension);
    }

    public static List<FileExtensionMIME> getByMimeType(String mimeType) {
        return getInstance().getByMimeType_(mimeType);
    }

    @NonNull
    public static String getExtensionByContentType(String contentType) {
        int index;
        if ((index = contentType.indexOf(";")) > 0) {
            contentType = contentType.substring(0, index);
        }
        List<FileExtensionMIME> fileExtensionMIMEList = FileExtensionMIMEResource.getByMimeType(contentType);
        if (!fileExtensionMIMEList.isEmpty()) {
            return fileExtensionMIMEList.get(0).extension().substring(1);
        }
        if ((index = contentType.indexOf("/")) > 0) {
            String subType = contentType.substring(index + 1);
            if ((index = subType.indexOf("+")) > 0) {
                subType = subType.substring(0, index);
            }
            return subType;
        }
        return contentType;
    }

    private static FileExtensionMIMEResource getInstance() {
        FileExtensionMIMEResource f;
        if (INSTANCE == null || (f = INSTANCE.get()) == null) {
            synchronized (FileExtensionMIMEResource.class) {
                if (INSTANCE == null || (f = INSTANCE.get()) == null) {
                    f = new FileExtensionMIMEResource();
                    INSTANCE = new WeakReference<>(f);
                }
            }
        }
        return f;
    }

    private FileExtensionMIME getByExtension_(String extension) {
        extension = extension.trim().toLowerCase();
        for (FileExtensionMIME fileExtensionMIME : FILE_EXTENSION_MIMES) {
            if (fileExtensionMIME.extension().equals(extension)) {
                return fileExtensionMIME;
            }
        }
        return null;
    }

    private List<FileExtensionMIME> getByMimeType_(String mimeType) {
        mimeType = mimeType.trim().toLowerCase();
        List<FileExtensionMIME> fileExtensionMIMEs = new ArrayList<>();
        for (FileExtensionMIME fileExtensionMIME : FILE_EXTENSION_MIMES) {
            if (fileExtensionMIME.mimeType().equals(mimeType)) {
                fileExtensionMIMEs.add(fileExtensionMIME);
            }
        }
        return fileExtensionMIMEs;
    }

    private final FileExtensionMIME[] FILE_EXTENSION_MIMES = {
            new FileExtensionMIME(".tif", "application/octet-stream"),
            new FileExtensionMIME(".tif", "image/tiff"),
            new FileExtensionMIME(".001", "application/x-001"),
            new FileExtensionMIME(".301", "application/x-301"),
            new FileExtensionMIME(".323", "text/h323"),
            new FileExtensionMIME(".906", "application/x-906"),
            new FileExtensionMIME(".907", "drawing/907"),
            new FileExtensionMIME(".a11", "application/x-a11"),
            new FileExtensionMIME(".acp", "audio/x-mei-aac"),
            new FileExtensionMIME(".ai", "application/postscript"),
            new FileExtensionMIME(".aif", "audio/aiff"),
            new FileExtensionMIME(".aifc", "audio/aiff"),
            new FileExtensionMIME(".aiff", "audio/aiff"),
            new FileExtensionMIME(".anv", "application/x-anv"),
            new FileExtensionMIME(".asa", "text/asa"),
            new FileExtensionMIME(".asf", "video/x-ms-asf"),
            new FileExtensionMIME(".asp", "text/asp"),
            new FileExtensionMIME(".asx", "video/x-ms-asf"),
            new FileExtensionMIME(".au", "audio/basic"),
            new FileExtensionMIME(".avi", "video/avi"),
            new FileExtensionMIME(".awf", "application/vnd.adobe.workflow"),
            new FileExtensionMIME(".biz", "text/xml"),
            new FileExtensionMIME(".bmp", "application/x-bmp"),
            new FileExtensionMIME(".bot", "application/x-bot"),
            new FileExtensionMIME(".c4t", "application/x-c4t"),
            new FileExtensionMIME(".c90", "application/x-c90"),
            new FileExtensionMIME(".cal", "application/x-cals"),
            new FileExtensionMIME(".cat", "application/vnd.ms-pki.seccat"),
            new FileExtensionMIME(".cdf", "application/x-netcdf"),
            new FileExtensionMIME(".cdr", "application/x-cdr"),
            new FileExtensionMIME(".cel", "application/x-cel"),
            new FileExtensionMIME(".cer", "application/x-x509-ca-cert"),
            new FileExtensionMIME(".cg4", "application/x-g4"),
            new FileExtensionMIME(".cgm", "application/x-cgm"),
            new FileExtensionMIME(".cit", "application/x-cit"),
            new FileExtensionMIME(".class", "java/*"),
            new FileExtensionMIME(".cml", "text/xml"),
            new FileExtensionMIME(".cmp", "application/x-cmp"),
            new FileExtensionMIME(".cmx", "application/x-cmx"),
            new FileExtensionMIME(".cot", "application/x-cot"),
            new FileExtensionMIME(".crl", "application/pkix-crl"),
            new FileExtensionMIME(".crt", "application/x-x509-ca-cert"),
            new FileExtensionMIME(".csi", "application/x-csi"),
            new FileExtensionMIME(".css", "text/css"),
            new FileExtensionMIME(".cut", "application/x-cut"),
            new FileExtensionMIME(".dbf", "application/x-dbf"),
            new FileExtensionMIME(".dbm", "application/x-dbm"),
            new FileExtensionMIME(".dbx", "application/x-dbx"),
            new FileExtensionMIME(".dcd", "text/xml"),
            new FileExtensionMIME(".dcx", "application/x-dcx"),
            new FileExtensionMIME(".der", "application/x-x509-ca-cert"),
            new FileExtensionMIME(".dgn", "application/x-dgn"),
            new FileExtensionMIME(".dib", "application/x-dib"),
            new FileExtensionMIME(".dll", "application/x-msdownload"),
            new FileExtensionMIME(".doc", "application/msword"),
            new FileExtensionMIME(".dot", "application/msword"),
            new FileExtensionMIME(".drw", "application/x-drw"),
            new FileExtensionMIME(".dtd", "text/xml"),
            new FileExtensionMIME(".dwf", "Model/vnd.dwf"),
            new FileExtensionMIME(".dwf", "application/x-dwf"),
            new FileExtensionMIME(".dwg", "application/x-dwg"),
            new FileExtensionMIME(".dxb", "application/x-dxb"),
            new FileExtensionMIME(".dxf", "application/x-dxf"),
            new FileExtensionMIME(".edn", "application/vnd.adobe.edn"),
            new FileExtensionMIME(".eml", "message/rfc822"),
            new FileExtensionMIME(".ent", "text/xml"),
            new FileExtensionMIME(".epi", "application/x-epi"),
            new FileExtensionMIME(".eps", "application/x-ps"),
            new FileExtensionMIME(".eps", "application/postscript"),
            new FileExtensionMIME(".etd", "application/x-ebx"),
            new FileExtensionMIME(".exe", "application/x-msdownload"),
            new FileExtensionMIME(".fax", "image/fax"),
            new FileExtensionMIME(".fdf", "application/vnd.fdf"),
            new FileExtensionMIME(".fif", "application/fractals"),
            new FileExtensionMIME(".fo", "text/xml"),
            new FileExtensionMIME(".frm", "application/x-frm"),
            new FileExtensionMIME(".g4", "application/x-g4"),
            new FileExtensionMIME(".gbr", "application/x-gbr"),
            new FileExtensionMIME(".gif", "image/gif"),
            new FileExtensionMIME(".gl2", "application/x-gl2"),
            new FileExtensionMIME(".gp4", "application/x-gp4"),
            new FileExtensionMIME(".hgl", "application/x-hgl"),
            new FileExtensionMIME(".hmr", "application/x-hmr"),
            new FileExtensionMIME(".hpg", "application/x-hpgl"),
            new FileExtensionMIME(".hpl", "application/x-hpl"),
            new FileExtensionMIME(".hqx", "application/mac-binhex40"),
            new FileExtensionMIME(".hrf", "application/x-hrf"),
            new FileExtensionMIME(".hta", "application/hta"),
            new FileExtensionMIME(".htc", "text/x-component"),
            new FileExtensionMIME(".htm", "text/html"),
            new FileExtensionMIME(".html", "text/html"),
            new FileExtensionMIME(".htt", "text/webviewhtml"),
            new FileExtensionMIME(".htx", "text/html"),
            new FileExtensionMIME(".icb", "application/x-icb"),
            new FileExtensionMIME(".ico", "image/x-icon"),
            new FileExtensionMIME(".ico", "application/x-ico"),
            new FileExtensionMIME(".iff", "application/x-iff"),
            new FileExtensionMIME(".ig4", "application/x-g4"),
            new FileExtensionMIME(".igs", "application/x-igs"),
            new FileExtensionMIME(".iii", "application/x-iphone"),
            new FileExtensionMIME(".img", "application/x-img"),
            new FileExtensionMIME(".ins", "application/x-internet-signup"),
            new FileExtensionMIME(".isp", "application/x-internet-signup"),
            new FileExtensionMIME(".IVF", "video/x-ivf"),
            new FileExtensionMIME(".java", "java/*"),
            new FileExtensionMIME(".jpeg", "image/jpeg"),
            new FileExtensionMIME(".jpe", "image/jpeg"),
            new FileExtensionMIME(".jfif", "image/jpeg"),
            new FileExtensionMIME(".jpe", "application/x-jpe"),
            new FileExtensionMIME(".jpg", "image/jpeg"),
            new FileExtensionMIME(".jpg", "application/x-jpg"),
            new FileExtensionMIME(".js", "application/x-javascript"),
            new FileExtensionMIME(".jsp", "text/html"),
            new FileExtensionMIME(".la1", "audio/x-liquid-file"),
            new FileExtensionMIME(".lar", "application/x-laplayer-reg"),
            new FileExtensionMIME(".latex", "application/x-latex"),
            new FileExtensionMIME(".lavs", "audio/x-liquid-secure"),
            new FileExtensionMIME(".lbm", "application/x-lbm"),
            new FileExtensionMIME(".lmsff", "audio/x-la-lms"),
            new FileExtensionMIME(".ls", "application/x-javascript"),
            new FileExtensionMIME(".ltr", "application/x-ltr"),
            new FileExtensionMIME(".m1v", "video/x-mpeg"),
            new FileExtensionMIME(".m2v", "video/x-mpeg"),
            new FileExtensionMIME(".m3u", "audio/mpegurl"),
            new FileExtensionMIME(".m4e", "video/mpeg4"),
            new FileExtensionMIME(".mac", "application/x-mac"),
            new FileExtensionMIME(".man", "application/x-troff-man"),
            new FileExtensionMIME(".math", "text/xml"),
            new FileExtensionMIME(".mdb", "application/msaccess"),
            new FileExtensionMIME(".mdb", "application/x-mdb"),
            new FileExtensionMIME(".mfp", "application/x-shockwave-flash"),
            new FileExtensionMIME(".mht", "message/rfc822"),
            new FileExtensionMIME(".mhtml", "message/rfc822"),
            new FileExtensionMIME(".mi", "application/x-mi"),
            new FileExtensionMIME(".mid", "audio/mid"),
            new FileExtensionMIME(".midi", "audio/mid"),
            new FileExtensionMIME(".mil", "application/x-mil"),
            new FileExtensionMIME(".mml", "text/xml"),
            new FileExtensionMIME(".mnd", "audio/x-musicnet-download"),
            new FileExtensionMIME(".mns", "audio/x-musicnet-stream"),
            new FileExtensionMIME(".mocha", "application/x-javascript"),
            new FileExtensionMIME(".movie", "video/x-sgi-movie"),
            new FileExtensionMIME(".mp1", "audio/mp1"),
            new FileExtensionMIME(".mp2", "audio/mp2"),
            new FileExtensionMIME(".mp2v", "video/mpeg"),
            new FileExtensionMIME(".mp3", "audio/mp3"),
            new FileExtensionMIME(".mp4", "video/mpeg4"),
            new FileExtensionMIME(".mpa", "video/x-mpg"),
            new FileExtensionMIME(".mpd", "application/vnd.ms-project"),
            new FileExtensionMIME(".mpe", "video/x-mpeg"),
            new FileExtensionMIME(".mpeg", "video/mpg"),
            new FileExtensionMIME(".mpg", "video/mpg"),
            new FileExtensionMIME(".mpga", "audio/rn-mpeg"),
            new FileExtensionMIME(".mpp", "application/vnd.ms-project"),
            new FileExtensionMIME(".mps", "video/x-mpeg"),
            new FileExtensionMIME(".mpt", "application/vnd.ms-project"),
            new FileExtensionMIME(".mpv", "video/mpg"),
            new FileExtensionMIME(".mpv2", "video/mpeg"),
            new FileExtensionMIME(".mpw", "application/vnd.ms-project"),
            new FileExtensionMIME(".mpx", "application/vnd.ms-project"),
            new FileExtensionMIME(".mtx", "text/xml"),
            new FileExtensionMIME(".mxp", "application/x-mxp"),
            new FileExtensionMIME(".myd", "application/x-my-database"),
            new FileExtensionMIME(".myi", "application/x-my-database"),
            new FileExtensionMIME(".nc", "application/x-netcdf"),
            new FileExtensionMIME(".ncm", "application/vnd.nokia.configuration-message"),
            new FileExtensionMIME(".nif", "application/x-nif"),
            new FileExtensionMIME(".nix", "application/x-nix"),
            new FileExtensionMIME(".nma", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".nmb", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".nmc", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".nmd", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".nme", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".nms", "application/vnd.nokia.theme"),
            new FileExtensionMIME(".ntf", "application/x-ntf"),
            new FileExtensionMIME(".nzb", "application/x-nzb"),
            new FileExtensionMIME(".odc", "text/xml"),
            new FileExtensionMIME(".odf", "application/vnd.oasis.opendocument.formula"),
            new FileExtensionMIME(".odg", "application/vnd.oasis.opendocument.graphics"),
            new FileExtensionMIME(".odi", "application/vnd.oasis.opendocument.image"),
            new FileExtensionMIME(".odm", "application/vnd.oasis.opendocument.text-master"),
            new FileExtensionMIME(".odp", "application/vnd.oasis.opendocument.presentation"),
            new FileExtensionMIME(".ods", "application/vnd.oasis.opendocument.spreadsheet"),
            new FileExtensionMIME(".odt", "application/vnd.oasis.opendocument.text"),
            new FileExtensionMIME(".oga", "audio/ogg"),
            new FileExtensionMIME(".ogv", "video/ogg"),
            new FileExtensionMIME(".ogx", "application/ogg"),
            new FileExtensionMIME(".spx", "audio/ogg"),
            new FileExtensionMIME(".opus", "audio/opus"),
            new FileExtensionMIME(".otf", "application/x-font-otf"),
            new FileExtensionMIME(".pdb", "application/x-pdb"),
            new FileExtensionMIME(".pdf", "application/pdf"),
            new FileExtensionMIME(".pgm", "image/x-portable-graymap"),
            new FileExtensionMIME(".pic", "image/x-pic"),
            new FileExtensionMIME(".pict", "image/x-pict"),
            new FileExtensionMIME(".pkg", "application/x-newton-compatible-pkg"),
            new FileExtensionMIME(".pl", "text/x-perl"),
            new FileExtensionMIME(".plg", "application/x-perl"),
            new FileExtensionMIME(".pls", "audio/x-scpls"),
            new FileExtensionMIME(".png", "image/png"),
            new FileExtensionMIME(".pnm", "image/x-portable-anymap"),
            new FileExtensionMIME(".pot", "application/vnd.ms-powerpoint"),
            new FileExtensionMIME(".potm", "application/vnd.ms-powerpoint.template.macroenabled.12"),
            new FileExtensionMIME(".potx", "application/vnd.openxmlformats-officedocument.presentationml.template"),
            new FileExtensionMIME(".ppam", "application/vnd.ms-powerpoint.addin.macroenabled.12"),
            new FileExtensionMIME(".ppm", "image/x-portable-pixmap"),
            new FileExtensionMIME(".pps", "application/vnd.ms-powerpoint"),
            new FileExtensionMIME(".ppsm", "application/vnd.ms-powerpoint.slideshow.macroenabled.12"),
            new FileExtensionMIME(".ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow"),
            new FileExtensionMIME(".ptm", "application/vnd.ms-powerpoint"),
            new FileExtensionMIME(".ptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
            new FileExtensionMIME(".pub", "application/x-mspublisher"),
            new FileExtensionMIME(".qt", "video/quicktime"),
            new FileExtensionMIME(".qti", "image/x-quicktime"),
            new FileExtensionMIME(".qtif", "image/x-quicktime"),
            new FileExtensionMIME(".ra", "audio/x-pn-realaudio"),
            new FileExtensionMIME(".ram", "audio/x-pn-realaudio"),
            new FileExtensionMIME(".rar", "application/x-rar-compressed"),
            new FileExtensionMIME(".ras", "image/x-cmu-raster"),
            new FileExtensionMIME(".rdf", "text/xml"),
            new FileExtensionMIME(".ref", "text/xml"),
            new FileExtensionMIME(".rgb", "image/x-rgb"),
            new FileExtensionMIME(".rm", "application/vnd.rn-realmedia"),
            new FileExtensionMIME(".rmi", "audio/mid"),
            new FileExtensionMIME(".rmp", "audio/x-pn-realaudio-plugin"),
            new FileExtensionMIME(".rpm", "audio/x-pn-realaudio-plugin"),
            new FileExtensionMIME(".rtf", "application/rtf"),
            new FileExtensionMIME(".rtf", "text/rtf"),
            new FileExtensionMIME(".rv", "video/vnd.rn-realvideo"),
            new FileExtensionMIME(".s3m", "audio/s3m"),
            new FileExtensionMIME(".sdp", "application/sdp"),
            new FileExtensionMIME(".sea", "application/x-stuffit"),
            new FileExtensionMIME(".sfv", "text/plain"),
            new FileExtensionMIME(".sgf", "application/x-sgf"),
            new FileExtensionMIME(".sgi", "image/x-sgi"),
            new FileExtensionMIME(".sh", "application/x-sh"),
            new FileExtensionMIME(".shar", "application/x-shar"),
            new FileExtensionMIME(".sig", "application/pgp-signature"),
            new FileExtensionMIME(".sil", "audio/silk"),
            new FileExtensionMIME(".sik", "text/x-sik"),
            new FileExtensionMIME(".sit", "application/x-stuffit"),
            new FileExtensionMIME(".smi", "application/smil"),
            new FileExtensionMIME(".smil", "application/smil"),
            new FileExtensionMIME(".smk", "application/x-smk"),
            new FileExtensionMIME(".snd", "audio/basic"),
            new FileExtensionMIME(".sol", "application/sol"),
            new FileExtensionMIME(".squ", "audio/x-sq"),
            new FileExtensionMIME(".sri", "application/sri"),
            new FileExtensionMIME(".srw", "application/srw"),
            new FileExtensionMIME(".swf", "application/x-shockwave-flash"),
            new FileExtensionMIME(".swt", "application/x-swt"),
            new FileExtensionMIME(".syk", "application/x-syk"),
            new FileExtensionMIME(".syl", "application/syl"),
            new FileExtensionMIME(".t2t", "text/t2t"),
            new FileExtensionMIME(".tar", "application/x-tar"),
            new FileExtensionMIME(".tcl", "application/x-tcl"),
            new FileExtensionMIME(".tex", "text/x-tex"),
            new FileExtensionMIME(".texi", "text/x-texinfo"),
            new FileExtensionMIME(".texinfo", "text/x-texinfo"),
            new FileExtensionMIME(".tgz", "application/x-compressed-tar"),
            new FileExtensionMIME(".thmx", "application/vnd.ms-officetheme"),
            new FileExtensionMIME(".tiff", "image/tiff"),
            new FileExtensionMIME(".tif", "image/tiff"),
            new FileExtensionMIME(".torrent", "application/x-bittorrent"),
            new FileExtensionMIME(".tsv", "text/tab-separated-values"),
            new FileExtensionMIME(".txt", "text/plain"),
            new FileExtensionMIME(".uls", "text/uls"),
            new FileExtensionMIME(".url", "application/x-url"),
            new FileExtensionMIME(".vcd", "application/x-cdlink"),
            new FileExtensionMIME(".vcf", "text/x-vcard"),
            new FileExtensionMIME(".vcs", "text/x-vcalendar"),
            new FileExtensionMIME(".vda", "application/x-vda"),
            new FileExtensionMIME(".vmdk", "application/x-vmdk"),
            new FileExtensionMIME(".vms", "application/x-vms"),
            new FileExtensionMIME(".vob", "video/x-ms-vob"),
            new FileExtensionMIME(".voc", "audio/voc"),
            new FileExtensionMIME(".vor", "application/x-voodoo"),
            new FileExtensionMIME(".vox", "audio/vox"),
            new FileExtensionMIME(".vpx", "application/x-vpx"),
            new FileExtensionMIME(".vrml", "model/vrml"),
            new FileExtensionMIME(".vsd", "application/vnd.visio"),
            new FileExtensionMIME(".vss", "application/vnd.visio"),
            new FileExtensionMIME(".vst", "application/vnd.visio"),
            new FileExtensionMIME(".vst", "application/x-visio"),
            new FileExtensionMIME(".vsw", "application/x-visio"),
            new FileExtensionMIME(".vsx", "application/vnd.visio"),
            new FileExtensionMIME(".vtx", "application/vnd.visio"),
            new FileExtensionMIME(".wav", "audio/wav"),
            new FileExtensionMIME(".wax", "audio/x-ms-wax"),
            new FileExtensionMIME(".wbmp", "image/vnd.wap.wbmp"),
            new FileExtensionMIME(".webm", "video/webm"),
            new FileExtensionMIME(".webp", "image/webp"),
            new FileExtensionMIME(".wks", "application/x-wks"),
            new FileExtensionMIME(".wm", "video/x-ms-wm"),
            new FileExtensionMIME(".wma", "audio/x-ms-wma"),
            new FileExtensionMIME(".wmd", "application/x-ms-wmd"),
            new FileExtensionMIME(".wmf", "application/x-wmf"),
            new FileExtensionMIME(".wml", "text/vnd.wap.wml"),
            new FileExtensionMIME(".wmlc", "application/vnd.wap.wmlc"),
            new FileExtensionMIME(".wmv", "video/x-ms-wmv"),
            new FileExtensionMIME(".wmx", "video/x-ms-wmx"),
            new FileExtensionMIME(".wmz", "application/x-ms-wmz"),
            new FileExtensionMIME(".woff", "application/font-woff"),
            new FileExtensionMIME(".woff2", "application/font-woff2"),
            new FileExtensionMIME(".wpd", "application/vnd.wordperfect"),
            new FileExtensionMIME(".wpl", "application/vnd.ms-wpl"),
            new FileExtensionMIME(".wps", "application/vnd.ms-works"),
            new FileExtensionMIME(".wsc", "text/scriptlet"),
            new FileExtensionMIME(".wsdl", "text/xml"),
            new FileExtensionMIME(".wvx", "video/x-ms-wvx"),
            new FileExtensionMIME(".x3d", "application/x3d+xml"),
            new FileExtensionMIME(".xap", "application/x-silverlight-app"),
            new FileExtensionMIME(".xar", "application/x-xar"),
            new FileExtensionMIME(".xht", "application/xhtml+xml"),
            new FileExtensionMIME(".xhtml", "application/xhtml+xml"),
            new FileExtensionMIME(".xls", "application/vnd.ms-excel"),
            new FileExtensionMIME(".xlsm", "application/vnd.ms-excel.sheet.macroenabled.12"),
            new FileExtensionMIME(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
            new FileExtensionMIME(".xlt", "application/vnd.ms-excel"),
            new FileExtensionMIME(".xltm", "application/vnd.ms-excel.template.macroenabled.12"),
            new FileExtensionMIME(".xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template"),
            new FileExtensionMIME(".xvml", "application/x-vnd.microsoftexcel"),
            new FileExtensionMIME(".xz", "application/x-xz"),
            new FileExtensionMIME(".yaml", "text/yaml"),
            new FileExtensionMIME(".yml", "text/yaml"),
            new FileExtensionMIME(".zip", "application/zip")
    };
}